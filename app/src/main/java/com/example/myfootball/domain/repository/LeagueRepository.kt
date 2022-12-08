package com.example.myfootball.domain.repository

import kotlinx.coroutines.flow.Flow

interface LeagueRepository {
    suspend fun fetchLeagues()
    suspend fun getAllLeaguesName(): Flow<List<String>>
}