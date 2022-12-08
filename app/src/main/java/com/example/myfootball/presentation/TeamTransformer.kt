package com.example.myfootball.presentation

import com.example.myfootball.data.model.Team
import com.example.myfootball.presentation.model.TeamDetailsUiModel
import com.example.myfootball.presentation.model.TeamUi
import javax.inject.Inject

class TeamTransformer @Inject constructor() {

    internal fun transformTeamsToUiModel(team: List<Team>) =
        team.map { this.transformTeamsToUiModel(it) }

    private fun transformTeamsToUiModel(team: Team) =
        with(team) {
            TeamUi(
                teamName = nameTeam,
                teamLogo = badge
            )
        }

    internal fun transformTeamDetailsUiModel(team: Team) =
        with(team) {
            TeamDetailsUiModel(
                countryName = country,
                bannerImage = banner,
                leagueName = league,
                descriptionTeam = descriptionFR,
                teamName = nameTeam
            )
        }
}
