package com.example.myfootball.data.remote

import com.example.myfootball.data.remote.ApiConstants.SEARCH_LEAGUES_ENDPOINT
import com.example.myfootball.data.remote.ApiConstants.SEARCH_TEAM_DETAILS_ENDPOINT
import com.example.myfootball.data.remote.ApiConstants.TEAMS_BY_LEAGUE_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(SEARCH_LEAGUES_ENDPOINT)
    suspend fun fetchAllLeague(): LeagueResponse
    @GET(TEAMS_BY_LEAGUE_ENDPOINT)
    suspend fun getAllTeamsByLeague(@Query("l") league: String): TeamResponse
    @GET(SEARCH_TEAM_DETAILS_ENDPOINT)
    suspend fun getTeamDetails(@Query("t") team: String): TeamResponse
}