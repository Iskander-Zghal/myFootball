package com.example.myfootball.data.mapper

import com.example.myfootball.data.model.League
import com.example.myfootball.data.remote.LeagueResponse
import javax.inject.Inject

class LeagueMapper @Inject constructor() {
    internal fun transformLeagueResponseToLeague(response: LeagueResponse) =
        response.leagues.map {
            with(it) {
                League(
                    idLeague = idLeague,
                    strLeague = strLeague,
                )
            }
        }
}