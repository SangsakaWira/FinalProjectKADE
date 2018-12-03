package com.example.sangsakawira.matchsub2.UI.matches

import com.example.sangsakawira.matchsub2.Model.EventsItem

interface MatchesView {

    fun showLoading()

    fun hideLoading()

    fun showPlaceholder()

    fun hidePlaceholder()

    fun showMatches(matches: List<EventsItem>)

}