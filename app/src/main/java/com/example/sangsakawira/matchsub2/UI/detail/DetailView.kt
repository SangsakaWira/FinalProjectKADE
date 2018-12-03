package com.example.sangsakawira.matchsub2.UI.detail

import com.example.sangsakawira.matchsub2.Model.EventsItem

interface DetailView {

    fun getData(matches: List<EventsItem>)

}