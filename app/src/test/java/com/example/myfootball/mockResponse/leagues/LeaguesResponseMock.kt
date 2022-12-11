package com.example.myfootball.mockResponse.leagues

import com.example.myfootball.data.remote.LeagueDetailsResponse
import com.example.myfootball.data.remote.LeagueResponse

object LeaguesResponseMock {
    val leaguesResponse = LeagueResponse(
        leagues = listOf(
            LeagueDetailsResponse(idLeague = "4328", strLeague = "English Premier League"),
            LeagueDetailsResponse(idLeague = "4329", strLeague = "English League Championship"),
            LeagueDetailsResponse(idLeague = "4330", strLeague = "Scottish Premier League"),
            LeagueDetailsResponse(idLeague = "4331", strLeague = "German Bundesliga"),
            LeagueDetailsResponse(idLeague = "4332", strLeague = "Italian Serie A"),
            LeagueDetailsResponse(idLeague = "4334", strLeague = "French Ligue 1"),
            LeagueDetailsResponse(idLeague = "4335", strLeague = "Spanish La Liga")
        )
    )
}