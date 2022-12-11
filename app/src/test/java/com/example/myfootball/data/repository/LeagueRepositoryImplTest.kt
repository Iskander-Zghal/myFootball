package com.example.myfootball.data.repository

import com.example.myfootball.data.local.room.LeagueDao
import com.example.myfootball.data.mapper.LeagueMapper
import com.example.myfootball.data.remote.ApiService
import com.example.myfootball.mockResponse.leagues.LeaguesMock.leagues
import com.example.myfootball.mockResponse.leagues.LeaguesResponseMock.leaguesResponse
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
import org.mockito.BDDMockito.then
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockitoExtension::class)
internal class LeagueRepositoryImplTest {


    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var mapper: LeagueMapper

    @Mock
    private lateinit var dao: LeagueDao

    private val scheduler = TestCoroutineScheduler()
    private val mainDispatcher = StandardTestDispatcher(scheduler)

    @InjectMocks
    private lateinit var leagueRepositoryImpl: LeagueRepositoryImpl

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(mainDispatcher)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchAllLeague() = runTest {
        // Given
        lenientGiven(apiService.fetchAllLeague()).willReturn(leaguesResponse)

        leagueRepositoryImpl.fetchLeagues()

        then(mapper).should().transformLeagueResponseToLeague(response = leaguesResponse)
        then(dao).should().deleteAllLeagues()
        then(dao).should().insertAll(mapper.transformLeagueResponseToLeague(response = leaguesResponse))
    }

    @Test
    fun getAllLeaguesName() = runTest {
        // Given
        leagueRepositoryImpl.getAllLeaguesName()

        //Then
        then(dao).should().getAllLeaguesName()
    }
}