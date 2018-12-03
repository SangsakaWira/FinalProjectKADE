package com.example.sangsakawira.matchsub2.Model.api

import java.net.URL

object ApiRepository {

    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}