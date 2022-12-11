package com.example.myfootball.presentation

import com.example.myfootball.domain.intercator.LeagueIntercator
import com.example.myfootball.domain.intercator.TeamsDetailsIntercator
import com.example.myfootball.domain.intercator.TeamsIntercator
import com.example.myfootball.mockResponse.teams.TeamsMock.teams
import com.example.myfootball.presentation.UiModelMock.teamsDetailsDisplayModel
import com.example.myfootball.presentation.UiModelMock.teamsDisplayModel
import com.example.myfootball.presentation.model.TeamDetailsViewState
import com.example.myfootball.presentation.model.TeamDetailsViewState.Ready
import com.example.myfootball.presentation.model.TeamsViewState
import com.example.myfootball.util.Result.Error
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
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockitoExtension::class)
internal class TeamsViewModelTest {
    @Mock
    private lateinit var leagueIntercator: LeagueIntercator

    @Mock
    private lateinit var teamsIntercator: TeamsIntercator

    @Mock
    private lateinit var teamsDetailsIntercator: TeamsDetailsIntercator

    @Mock
    private lateinit var transformer: TeamTransformer

    private lateinit var teamsViewModel: TeamsViewModel

    private val scheduler = TestCoroutineScheduler()
    private val mainDispatcher = StandardTestDispatcher(scheduler)


    @BeforeEach
    fun setUp() {
        teamsViewModel = TeamsViewModel(
            leagueIntercator, teamsIntercator, teamsDetailsIntercator, transformer, mainDispatcher
        )

        Dispatchers.setMain(mainDispatcher)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getAllLeagues() = runTest {
        // When
        teamsViewModel.getAllLeagues()

        // Then
        then(leagueIntercator).should().getAllLeaguesName()
    }

    @Test
    fun `fetchTeams - when interactor return success -  return teamsDisplayModel`() = runTest {
        // Given
        lenientGiven(teamsIntercator.fetchTeamsByLeague(league = "gg")).willReturn(Success(teams))
        lenientGiven(transformer.transformTeamsToUiModel(team = teams)).willReturn(teamsDisplayModel)

        // When
        teamsViewModel.fetchTeams(teamName = "gg")

        //Then
        scheduler.advanceUntilIdle()
        assertThat(teamsViewModel.teamsViewState.value).isEqualTo(
            TeamsViewState.Ready(
                teamsDisplayModel
            )
        )
    }

    @Test
    fun `fetchTeams - when interactor return error -  return empty state`() = runTest {
        // Given
        lenientGiven(teamsIntercator.fetchTeamsByLeague(league = "gg")).willReturn(
            Error(
                message = "", data = null
            )
        )
        lenientGiven(transformer.transformTeamsToUiModel(team = teams)).willReturn(listOf())

        // When
        teamsViewModel.fetchTeams(teamName = "gg")

        //Then
        scheduler.advanceUntilIdle()
        assertThat(teamsViewModel.teamsViewState.value).isEqualTo(
            TeamsViewState.Empty
        )
    }

    @Test
    fun `getTeamDetails - when interactor return success -  return teamsDetailsDisplayModel`() =
        runTest {
            // Given
            given(teamsDetailsIntercator.fetchTeamDetails(teamDetails = "gg")).willReturn(
                Success(
                    teams[0]
                )
            )
            given(transformer.transformTeamDetailsUiModel(team = teams.first())).willReturn(
                teamsDetailsDisplayModel
            )
            // When
            teamsViewModel.getTeamDetails(teamName = "gg")


            //Then
            scheduler.advanceUntilIdle()
            assertThat(teamsViewModel.teamDetailsViewState.value).isEqualTo(
                Ready(
                    data = teamsDetailsDisplayModel
                )
            )
        }


    @Test
    fun `getTeamDetails - when interactor return error -  return empty state`() = runTest {
        // Given
        lenientGiven(teamsDetailsIntercator.fetchTeamDetails(teamDetails = "gg")).willReturn(
            Error(
                message = "", data = null
            )
        )
        lenientGiven(transformer.transformTeamDetailsUiModel(team = teams.first())).willReturn(
            teamsDetailsDisplayModel
        )
        // When
        teamsViewModel.getTeamDetails(teamName = "gg")

        // Then
        scheduler.advanceUntilIdle()
        assertThat(teamsViewModel.teamDetailsViewState.value).isEqualTo(
            TeamDetailsViewState.Empty
        )
    }
}