package com.example.myfootball.data.mapper

import com.example.myfootball.data.model.Team
import com.example.myfootball.data.remote.TeamResponse
import javax.inject.Inject

class TeamMapper @Inject constructor() {
    internal fun transformTeamsResponseToTeam(response: TeamResponse) =
        response.teams.map {
            with(it) {
                Team(
                    idTeam = idTeam,
                    nameTeam = strTeam,
                    league = strLeague,
                    badge = strTeamBadge,
                    descriptionFR = strDescriptionFR ?: "",
                    country = strCountry,
                    banner = strTeamBanner ?: ""
                )
            }
        }
}