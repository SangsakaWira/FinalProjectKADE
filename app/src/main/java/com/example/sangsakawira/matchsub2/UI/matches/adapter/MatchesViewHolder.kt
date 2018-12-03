package com.example.sangsakawira.matchsub2.UI.matches.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.sangsakawira.matchsub2.Model.EventsItem
import kotlinx.android.synthetic.main.item_matches.view.*

class MatchesViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: EventsItem) {

        with(itemView) {
            tv_date.text = item.strDate
            tv_time.text = item.strTime
            tv_home_name.text = item.strHomeTeam
            tv_away_name.text = item.strAwayTeam
            if(item.intHomeScore == null ||item.intAwayScore == null){
                tv_home_score.text = "-"
                tv_away_score.text = "-"
            }else{
                tv_home_score.text = item.intHomeScore.toString()
                tv_away_score.text = item.intAwayScore.toString()
            }

        }

    }
}