package com.example.sangsakawira.matchsub2.Model.network

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.jetbrains.anko.AnkoLogger


open class NetworkImage: AnkoLogger {

    fun get(url: String): String? {
        val request = Request.Builder().url(url).get().build()
        val client = OkHttpClient()
        val response: Response = client.newCall(request).execute()
        val jsonResponse = response.body()?.string()

        return jsonResponse
    }

}