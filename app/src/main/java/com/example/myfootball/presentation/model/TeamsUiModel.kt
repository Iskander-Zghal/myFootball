package com.example.myfootball.presentation.model


sealed class TeamsViewState {
    object Idle : TeamsViewState()
    data class Ready(val data: List<TeamUi>) : TeamsViewState()
    object Empty : TeamsViewState()
}

data class TeamUi(
    val teamName: String,
    val teamLogo: String,
)

