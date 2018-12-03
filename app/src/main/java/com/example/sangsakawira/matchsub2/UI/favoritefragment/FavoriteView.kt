package com.example.sangsakawira.matchsub2.UI.favoritefragment

import com.example.sangsakawira.matchsub2.Model.db.Favorite

interface FavoriteView {

    fun showLoading()

    fun hideLoading()

    fun showPlaceholder()

    fun hidePlaceholder()

    fun showMatches(matches: List<Favorite>?)

}