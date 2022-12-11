package com.example.myfootball.data.repository

import com.example.myfootball.data.remote.ApiService
import com.example.myfootball.mockResponse.leagues.LeaguesResponseMock.leaguesResponse
import com.example.myfootball.mockResponse.teams.TeamsResponseMock.teamsResponse
import com.example.myfootball.util.enqueue
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockitoExtension::class)
internal class ApiServiceTest {

    private lateinit var server: MockWebServer

    private lateinit var apiService: ApiService

    private val scheduler = TestCoroutineScheduler()
    private val mainDispatcher = StandardTestDispatcher(scheduler)

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(mainDispatcher)
        server = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(server.url(""))//We will use MockWebServers url
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(ApiService::class.java)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        server.shutdown()
    }

    @Test
    fun fetchAllLeague() = runTest {
        //Given
        server.enqueue("API_Leagues.json")

        //When
        val responseBody = apiService.fetchAllLeague()

        //then
        val recordedRequest = server.takeRequest()
        assertThat(responseBody.leagues).isEqualTo(leaguesResponse.leagues)
        assertThat(responseBody.leagues.size).isEqualTo(7)
        assertThat(recordedRequest.method).isEqualTo("GET")
        assertThat(recordedRequest.path).isEqualTo("/all_leagues.php")
    }

    @Test
    fun getAllTeamsByLeague() = runTest {
        // Given
        server.enqueue("API_Teams.json")

        // When
        val responseBody = apiService.getAllTeamsByLeague("French Ligue 01")

        // Then
        val recordedRequest = server.takeRequest()
        assertThat(responseBody.teams).isEqualTo(teamsResponse.teams)
        assertThat(recordedRequest.method).isEqualTo("GET")
        assertThat(recordedRequest.path).isEqualTo("/search_all_teams.php?l=French%20Ligue%2001")

    }

    @Test
    fun getTeamDetails() = runTest {
        // Given
        server.enqueue("API_Teams.json")

        // When
        val responseBody = apiService.getTeamDetails("jj")

        // Then
        val recordedRequest = server.takeRequest()
        assertThat(responseBody.teams).isEqualTo(teamsResponse.teams)
        assertThat(recordedRequest.method).isEqualTo("GET")
        assertThat(recordedRequest.path).isEqualTo("/searchteams.php?t=jj")
    }
}