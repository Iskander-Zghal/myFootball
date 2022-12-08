package com.example.myfootball.domain.repository

import com.example.myfootball.data.model.Team
import com.example.myfootball.util.Result

interface TeamDetailsRepository {
    suspend fun getTeamsDetails(teamName: String): Result<Team>
}