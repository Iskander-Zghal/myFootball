package com.example.myfootball.domain.repository

import com.example.myfootball.data.model.Team
import com.example.myfootball.util.Result

interface TeamsRepository {
    suspend fun getAllTeams(league: String): Result<List<Team>>
}