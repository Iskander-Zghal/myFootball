package com.example.myfootball.data.repository

import com.example.myfootball.data.mapper.TeamMapper
import com.example.myfootball.data.remote.ApiService
import com.example.myfootball.mockResponse.teams.TeamsMock.teams
import com.example.myfootball.mockResponse.teams.TeamsResponseMock.teamsResponse
import com.example.myfootball.util.Result.Success
import com.example.myfootball.util.lenientGiven
import com.example.myfootball.util.willReturn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockitoExtension::class)
internal class TeamDetailsRepositoryImplTest {
    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var mapper: TeamMapper

    @InjectMocks
    private lateinit var teamDetailsRepositoryImpl: TeamDetailsRepositoryImpl

    private val scheduler = TestCoroutineScheduler()
    private val mainDispatcher = StandardTestDispatcher(scheduler)

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(mainDispatcher)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getTeamDetails() = runTest {
        // Given
        lenientGiven(apiService.getTeamDetails("test")).willReturn(teamsResponse)
        lenientGiven(mapper.transformTeamsResponseToTeam(teamsResponse)).willReturn(teams)

        // When
        val result = teamDetailsRepositoryImpl.getTeamsDetails("test")

        // Then
        assertThat(result).isEqualTo(Success(teams.first()))
    }
}