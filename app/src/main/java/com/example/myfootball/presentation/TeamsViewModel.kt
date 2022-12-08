package com.example.myfootball.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfootball.IoDispatcher
import com.example.myfootball.domain.intercator.LeagueIntercator
import com.example.myfootball.domain.intercator.TeamsDetailsIntercator
import com.example.myfootball.domain.intercator.TeamsIntercator
import com.example.myfootball.presentation.model.TeamDetailsViewState
import com.example.myfootball.presentation.model.TeamDetailsViewState.*
import com.example.myfootball.presentation.model.TeamsViewState
import com.example.myfootball.presentation.model.TeamsViewState.Empty
import com.example.myfootball.presentation.model.TeamsViewState.Ready
import com.example.myfootball.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val leagueIntercator: LeagueIntercator,
    private val teamsIntercator: TeamsIntercator,
    private val teamsDetailsIntercator: TeamsDetailsIntercator,
    private val transformer: TeamTransformer,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _teamsViewState = MutableStateFlow<TeamsViewState>(value = TeamsViewState.Idle)
    val teamsViewState: StateFlow<TeamsViewState> = _teamsViewState

    private var _teamDetailsViewState =
        MutableStateFlow<TeamDetailsViewState>(value = Idle)
    val teamDetailsViewState: StateFlow<TeamDetailsViewState> = _teamDetailsViewState

    fun fetchLeagues() = viewModelScope.launch(dispatcher) { leagueIntercator.fetchLeagues() }

    suspend fun getAllLeagues() = leagueIntercator.getAllLeaguesName()

    fun fetchTeams(teamName: String) = viewModelScope.launch {
        when (val teams = teamsIntercator.fetchTeamsByLeague(league = teamName)) {
            is Result.Success -> {
                _teamsViewState.value = Ready(
                    data = transformer.transformTeamsToUiModel(team = teams.data)
                )
            }
            is Result.Error -> {
                _teamsViewState.value = Empty
            }
        }
    }

    fun getTeamDetails(teamName: String) {
        viewModelScope.launch {
            when (val teamsDetails =
                teamsDetailsIntercator.fetchTeamDetails(teamDetails = teamName)) {
                is Result.Success -> {
                    _teamDetailsViewState.value = Ready(
                        data = transformer.transformTeamDetailsUiModel(teamsDetails.data)
                    )
                }
                is Result.Error -> {
                    _teamDetailsViewState.value = TeamDetailsViewState.Empty
                }
            }
        }
    }
}

