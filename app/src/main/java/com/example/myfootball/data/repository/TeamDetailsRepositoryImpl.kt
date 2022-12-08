package com.example.myfootball.data.repository

import com.example.myfootball.data.mapper.TeamMapper
import com.example.myfootball.data.model.Team
import com.example.myfootball.data.remote.ApiService
import com.example.myfootball.domain.repository.TeamDetailsRepository
import com.example.myfootball.util.Result
import javax.inject.Inject

class TeamDetailsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val teamMapper: TeamMapper
) : TeamDetailsRepository {
    override suspend fun getTeamsDetails(teamName: String): Result<Team> {
        return try {
            val teamResponse = apiService.getTeamDetails(teamName)
            if (teamResponse.teams.isNotEmpty()) {
                Result.Success(teamMapper.transformTeamsResponseToTeam(teamResponse).first())
            } else Result.Error("No results found.")
        } catch (e: Exception) {
            Result.Error(e.localizedMessage ?: "An error occurred.")
        }
    }
}