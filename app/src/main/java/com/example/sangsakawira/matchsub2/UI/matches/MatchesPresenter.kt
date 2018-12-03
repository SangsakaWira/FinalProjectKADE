package com.example.sangsakawira.matchsub2.UI.matches

import android.content.Context
import com.example.sangsakawira.matchsub2.Model.api.ApiRepository
import com.example.sangsakawira.matchsub2.Model.EventsItem
import com.example.sangsakawira.matchsub2.Model.MatchesResponse
import com.example.sangsakawira.matchsub2.R
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MatchesPresenter(private val mView:MatchesView, private val context: Context?) {

    fun fetchMatches(type:String?){

        GlobalScope.launch(Dispatchers.Main) {

            //show loading
            mView.showLoading()

            //get data
            val response = GlobalScope.async {
                ApiRepository
                    .doRequest(
                        context?.getString(R.string.url_1)+"events${type}league.php?id=4328"
                    )
            }.await()

            //parse data
            val result = Gson()
                .fromJson<MatchesResponse>(
                    response, MatchesResponse::class.java
                )

            // tampilkan data
            if (result?.events != null) {
                mView.showMatches(result.events as List<EventsItem>)
            } else {
                mView.showPlaceholder()
            }

        }

    }

    fun MatchesPresenterForTesting(type:String?):MatchesResponse?{
        var resultForTest:MatchesResponse? = null

        GlobalScope.launch(Dispatchers.Main) {

            //show loading
            mView.showLoading()

            //get data
            val response = GlobalScope.async {
                ApiRepository
                    .doRequest(
                        context?.getString(R.string.url_1)+"events${type}league.php?id=4328"
                    )
            }.await()

            //parse data
            val result = Gson()
                .fromJson<MatchesResponse>(
                    response, MatchesResponse::class.java
                )

            resultForTest = result


        }
        return resultForTest

    }

}