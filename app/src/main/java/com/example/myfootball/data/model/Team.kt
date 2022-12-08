package com.example.myfootball.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    val idTeam: String,
    val nameTeam: String,
    val league: String,
    val badge: String,
    val descriptionFR: String,
    val country: String,
    val banner: String
) : Parcelable