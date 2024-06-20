package com.example.myfootball.presentation.model


sealed class TeamsViewState {
    data object Idle : TeamsViewState()
    data class Ready(val data: List<TeamUi>) : TeamsViewState()
    data object Empty : TeamsViewState()
}

data class TeamUi(
    val teamName: String,
    val teamLogo: String?,
)

