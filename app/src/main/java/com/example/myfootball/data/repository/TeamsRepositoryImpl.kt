package com.example.myfootball.data.repository

import com.example.myfootball.data.mapper.TeamMapper
import com.example.myfootball.data.model.Team
import com.example.myfootball.data.remote.ApiService
import com.example.myfootball.domain.repository.TeamsRepository
import com.example.myfootball.util.Result
import javax.inject.Inject
import kotlin.math.roundToInt

class TeamsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val teamMapper: TeamMapper
) : TeamsRepository {
    override suspend fun getAllTeams(league: String): Result<List<Team>> {
        return try {
            val teamResponse = apiService.getAllTeamsByLeague(league)
            if (teamResponse.teams.isNotEmpty()) {

                val teams = teamMapper.transformTeamsResponseToTeam(teamResponse)
                    .sortedByDescending { it.nameTeam }
                Result.Success(teams.subList(0, (teams.size / 2).toFloat().roundToInt()))
            } else Result.Error("No results found.")
        } catch (e: Exception) {
            Result.Error(e.localizedMessage ?: "An error occurred.")
        }
    }
}