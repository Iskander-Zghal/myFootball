package com.example.myfootball.domain.intercator

import com.example.myfootball.data.repository.LeagueRepositoryImpl
import javax.inject.Inject

class LeagueIntercator @Inject constructor(
    private val leagueRepository: LeagueRepositoryImpl
) {
    suspend fun fetchLeagues() = leagueRepository.fetchLeagues()
    suspend fun getAllLeaguesName() = leagueRepository.getAllLeaguesName()
}