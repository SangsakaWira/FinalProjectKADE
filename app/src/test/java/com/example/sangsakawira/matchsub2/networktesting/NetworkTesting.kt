package com.example.sangsakawira.matchsub2.networktesting

import com.example.sangsakawira.matchsub2.Model.network.NetworkImage
import org.junit.Test
import org.mockito.Mockito

class NetworkTesting {

    @Test
    fun testRequest() {
        val apiRepository = Mockito.mock(NetworkImage::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328"
        apiRepository.get(url)
        Mockito.verify(apiRepository).get(url)
    }


}