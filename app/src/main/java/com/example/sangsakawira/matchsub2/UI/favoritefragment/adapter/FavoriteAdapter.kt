package com.example.sangsakawira.matchsub2.UI.favoritefragment.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.sangsakawira.matchsub2.Model.db.Favorite
import com.example.sangsakawira.matchsub2.R
import com.example.sangsakawira.matchsub2.UI.detail.DetailActivity
import com.example.sangsakawira.matchsub2.UI.matches.adapter.FavoriteViewHolder
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivity

class FavoriteAdapter (private val items: List<Favorite>, val context: Context?)
    : RecyclerView.Adapter<FavoriteViewHolder>(),AnkoLogger{

    override fun onCreateViewHolder(container: ViewGroup, type: Int): FavoriteViewHolder {
        val inflater = LayoutInflater.from(container.context)
        return FavoriteViewHolder(inflater.inflate(R.layout.item_matches, container, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {

        holder.bind(items[position])
        val klub = items[position]


        holder.itemView.setOnClickListener {

            context?.startActivity<DetailActivity>("dataFavorite" to klub.idEvent)

        }

    }
}