package com.example.myfootball.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myfootball.data.model.League
import kotlinx.coroutines.flow.Flow

@Dao
interface LeagueDao {

    @Query("SELECT strLeague FROM leagues")
    fun getAllLeaguesName(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(leagues: List<League>)

    @Query("DELETE FROM leagues")
    suspend fun deleteAllLeagues()
}