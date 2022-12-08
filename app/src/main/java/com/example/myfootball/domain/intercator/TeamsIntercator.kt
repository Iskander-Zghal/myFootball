package com.example.myfootball.domain.intercator

import com.example.myfootball.data.repository.TeamsRepositoryImpl
import javax.inject.Inject

class TeamsIntercator @Inject constructor(
    private val teamRepository: TeamsRepositoryImpl
) {
    suspend fun fetchTeamsByLeague(league: String) = teamRepository.getAllTeams(league = league)
}