package com.example.myfootball.data.remote

import com.squareup.moshi.Json

data class LeagueResponse(@Json(name = "leagues") val leagues: List<LeagueDetailsResponse>)

data class LeagueDetailsResponse(
    @Json(name = "idLeague") val idLeague: String,
    @Json(name = "strLeague") val strLeague: String?,
)