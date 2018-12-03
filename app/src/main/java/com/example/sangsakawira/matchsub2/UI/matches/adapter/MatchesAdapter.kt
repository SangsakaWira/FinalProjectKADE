package com.example.sangsakawira.matchsub2.UI.matches.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.sangsakawira.matchsub2.Model.EventsItem
import com.example.sangsakawira.matchsub2.R
import com.example.sangsakawira.matchsub2.UI.detail.DetailActivity
import org.jetbrains.anko.startActivity

class MatchesAdapter (private val items: List<EventsItem>, val context: Context?) : RecyclerView.Adapter<MatchesViewHolder>(){

    override fun onCreateViewHolder(container: ViewGroup, type: Int): MatchesViewHolder {
        val inflater = LayoutInflater.from(container.context)
        return MatchesViewHolder(inflater.inflate(R.layout.item_matches, container, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        holder.bind(items[position])
        val klub = items[position]

        holder.itemView.setOnClickListener {
            context?.startActivity<DetailActivity>(context.getString(R.string.data) to klub)

        }

    }
}