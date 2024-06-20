package com.example.myfootball.data.remote

import com.squareup.moshi.Json

data class TeamResponse(
    @Json(name = "teams") val teams: List<TeamDetailsResponse>,
)

data class TeamDetailsResponse(
    @Json(name = "idTeam") val idTeam: String,
    @Json(name = "strTeam") val strTeam: String,
    @Json(name = "strLeague") val strLeague: String,
    @Json(name = "strTeamBadge") val strTeamBadge: String?,
    @Json(name = "strDescriptionFR") val strDescriptionFR: String?,
    @Json(name = "strCountry") val strCountry: String,
    @Json(name = "strTeamBanner") val strTeamBanner: String?,
)