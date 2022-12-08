package com.example.myfootball.data.di

import android.content.Context
import androidx.room.Room
import com.example.myfootball.data.local.room.LeagueDao
import com.example.myfootball.data.local.room.LeagueDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun provideMovieDao(database: LeagueDatabase): LeagueDao {
        return database.leagueDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): LeagueDatabase {
        return Room.databaseBuilder(
            appContext,
            LeagueDatabase::class.java,
            LeagueDatabase::class.java.simpleName
        ).build()
    }
}