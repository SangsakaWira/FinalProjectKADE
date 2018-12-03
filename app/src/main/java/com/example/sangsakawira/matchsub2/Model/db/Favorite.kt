package com.example.sangsakawira.matchsub2.Model.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Favorite(
                    val ID: Long?,
                    val idEvent: String?,
                    val strHomeTeam: String?,
                    val strAwayTeam: String?,
                    val dateEvent: String?,
                    val intHomeScore: String?,
                    val intAwayScore: String?
                    ):Parcelable{
    companion object {

        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val idEvent: String = "idEvent"
        const val strHomeTeam: String = "strHomeTeam"
        const val strAwayTeam: String = "strAwayTeam"
        const val dateEvent: String = "dateEvent"
        const val intHomeScore: String = "intHomeScore"
        const val intAwayScore: String = "intAwayScore"
    }


}