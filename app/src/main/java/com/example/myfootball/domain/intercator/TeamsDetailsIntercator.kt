package com.example.myfootball.domain.intercator

import com.example.myfootball.data.repository.TeamDetailsRepositoryImpl
import com.example.myfootball.domain.repository.TeamDetailsRepository
import javax.inject.Inject

class TeamsDetailsIntercator @Inject constructor(
    private val teamDetailsRepository: TeamDetailsRepositoryImpl
) {
    suspend fun fetchTeamDetails(teamDetails: String) = teamDetailsRepository.getTeamsDetails(teamDetails)
}