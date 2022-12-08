package com.example.myfootball.presentation.model


sealed class TeamDetailsViewState {
    object Idle : TeamDetailsViewState()
    data class Ready(val data: TeamDetailsUiModel) : TeamDetailsViewState()
    object Empty : TeamDetailsViewState()
}

data class TeamDetailsUiModel(
    val teamName: String,
    val countryName: String,
    val leagueName: String,
    val descriptionTeam: String,
    val bannerImage: String
)

