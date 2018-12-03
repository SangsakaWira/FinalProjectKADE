package com.example.sangsakawira.matchsub2.UI.detail

import android.content.Context
import com.example.sangsakawira.matchsub2.Model.EventsItem
import com.example.sangsakawira.matchsub2.Model.MatchesResponse
import com.example.sangsakawira.matchsub2.Model.api.ApiRepository
import com.example.sangsakawira.matchsub2.Model.network.NetworkImage
import com.example.sangsakawira.matchsub2.R
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DetailPresenter(private val mView:DetailView,private val context: Context,private val networkClient:NetworkImage) {

    fun fetchMatches(id:String){

        GlobalScope.launch(Dispatchers.Main) {

            //get data
            val response = GlobalScope.async {
                ApiRepository
                    .doRequest(
                        context.getString(R.string.url_1)+"lookupevent.php?id=${id}"
                    )
            }.await()

            //parse data
            val result = Gson()
                .fromJson<MatchesResponse>(
                    response, MatchesResponse::class.java
                )

            // tampilkan data
            if (result?.events != null) {
                mView.getData(result.events as List<EventsItem>)
            } else {

            }

        }

    }

}