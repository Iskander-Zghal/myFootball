package com.example.myfootball.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "leagues")
data class League(
    @PrimaryKey
    val idLeague: String,
    val strLeague: String?,
) : Parcelable