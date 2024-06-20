package com.example.myfootball.presentation.model


sealed class TeamDetailsViewState {
    data object Idle : TeamDetailsViewState()
    data class Ready(val data: TeamDetailsUiModel) : TeamDetailsViewState()
    data object Empty : TeamDetailsViewState()
}

data class TeamDetailsUiModel(
    val teamName: String? = null,
    val countryName: String? = null,
    val leagueName: String? = null,
    val descriptionTeam: String? = null,
    val bannerImage: String? = null,
)

