package com.example.myfootball.data.repository

import com.example.myfootball.data.local.room.LeagueDao
import com.example.myfootball.data.mapper.LeagueMapper
import com.example.myfootball.data.remote.ApiService
import com.example.myfootball.domain.repository.LeagueRepository
import com.example.myfootball.util.Result
import javax.inject.Inject

class LeagueRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val leagueMapper: LeagueMapper,
    private val leagueDao: LeagueDao
) : LeagueRepository {
    override suspend fun fetchLeagues() {
        try {
            val leagueResponse = apiService.fetchAllLeague()
            if (leagueResponse.leagues.isNotEmpty()) {
                leagueMapper.transformLeagueResponseToLeague(leagueResponse).let { data ->
                    Result.Success(data = data)
                }.apply {
                    leagueDao.deleteAllLeagues()
                    leagueDao.insertAll(data)
                }
            } else Result.Error("No results found.")
        } catch (e: Exception) {
            Result.Error(e.localizedMessage ?: "An error occurred.")
        }
    }

    override suspend fun getAllLeaguesName() = leagueDao.getAllLeaguesName()
}

