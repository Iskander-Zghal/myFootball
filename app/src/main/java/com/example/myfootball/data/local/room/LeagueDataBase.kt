package com.example.myfootball.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myfootball.data.model.League

@Database(entities = [League::class], version = 1)
abstract class LeagueDatabase : RoomDatabase() {
    abstract fun leagueDao(): LeagueDao
}