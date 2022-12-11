package com.example.myfootball.domain

import com.example.myfootball.data.repository.TeamsRepositoryImpl
import com.example.myfootball.domain.intercator.TeamsIntercator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.then
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockitoExtension::class)
internal class TeamsInterctorTest {

    @Mock
    lateinit var teamsRepositoryImpl: TeamsRepositoryImpl

    @InjectMocks
    private lateinit var intercator: TeamsIntercator

    private val scheduler = TestCoroutineScheduler()
    private val mainDispatcher = StandardTestDispatcher(scheduler)

    @BeforeEach
    internal fun setUp() {
        Dispatchers.setMain(mainDispatcher)
    }

    @AfterEach
    internal fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchTeamsByLeague() = runTest {
        // When
        intercator.fetchTeamsByLeague("jj")

        then(teamsRepositoryImpl).should().getAllTeams("jj")
        // Then
    }
}