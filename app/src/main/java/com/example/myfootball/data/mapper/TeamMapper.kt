package com.example.myfootball.data.mapper

import com.example.myfootball.data.model.Team
import com.example.myfootball.data.remote.TeamResponse
import javax.inject.Inject

class TeamMapper @Inject constructor() {
    internal fun transformTeamsResponseToTeam(teamResponse: TeamResponse) =
        teamResponse.teams.map {
            Team(
                idTeam = it.idTeam,
                nameTeam = it.strTeam,
                league = it.strLeague,
                badge = it.strTeamBadge,
                descriptionFR = it.strDescriptionFR ?: "",
                country = it.strCountry,
                banner = it.strTeamBanner ?: ""
            )
        }
}