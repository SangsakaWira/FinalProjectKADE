package com.example.sangsakawira.matchsub2.UI.favoritefragment

import android.content.Context
import com.example.sangsakawira.matchsub2.Model.db.Favorite
import com.example.sangsakawira.matchsub2.Model.db.MyDatabaseOpenHelper
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoritePresenter(private val mView: FavoriteView,private val database: MyDatabaseOpenHelper?) {

    fun fetchMatches(){
        mView.showLoading()
        mView.showMatches(getDatabase())
        mView.hideLoading()
    }

    fun getDatabase(): List<Favorite>? {
        val data  = database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
            result.parseList(classParser<Favorite>())
        }
        return data
    }

}

