package com.example.sangsakawira.matchsub2.databasetesting

import android.content.Context
import com.example.sangsakawira.matchsub2.Model.db.Favorite
import com.example.sangsakawira.matchsub2.Model.db.MyDatabaseOpenHelper
import com.example.sangsakawira.matchsub2.UI.favoritefragment.FavoritePresenter
import com.example.sangsakawira.matchsub2.UI.favoritefragment.FavoriteView
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DatabaseTesting {

    @Mock
    private  lateinit var presenter:FavoritePresenter

    @Mock
    private lateinit var view: FavoriteView

    @Mock private lateinit var databaseOpenHelper: MyDatabaseOpenHelper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = FavoritePresenter(view,databaseOpenHelper)
    }

    @Test
    fun getDataItemsFavourite() {
        var response:List<Favorite > = arrayListOf()
        Mockito.`when`(presenter.getDatabase())
            .thenReturn(response)
        view.showLoading()
        view.showMatches(response)
        view.hideLoading()
    }

}