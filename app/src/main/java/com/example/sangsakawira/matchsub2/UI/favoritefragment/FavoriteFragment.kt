package com.example.sangsakawira.matchsub2.UI.favoritefragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sangsakawira.matchsub2.Model.db.Favorite
import com.example.sangsakawira.matchsub2.Model.db.database
import com.example.sangsakawira.matchsub2.R
import com.example.sangsakawira.matchsub2.UI.favoritefragment.adapter.FavoriteAdapter
import org.jetbrains.anko.find

class FavoriteFragment : Fragment(),FavoriteView,SwipeRefreshLayout.OnRefreshListener{

    private lateinit var adapter: FavoriteAdapter
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var mFavorite:FavoritePresenter

    override fun onRefresh() {
        refreshLayout.isRefreshing = false
        dataItem.clear()
        mFavorite.fetchMatches()
        adapter.notifyDataSetChanged()
    }

    private lateinit var dataItem:ArrayList<Favorite>

    override fun showMatches(matches: List<Favorite>?) {
        dataItem.clear()
        dataItem.addAll(matches!!)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        refreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        refreshLayout.isRefreshing = false
    }

    override fun showPlaceholder() {

    }

    override fun hidePlaceholder() {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_favorites, container, false)
        dataItem = arrayListOf()
        refreshLayout = view.find(R.id.fragmentRefresh)
        val recycler:RecyclerView = view.find(R.id.rv_favorite)
        recycler.layoutManager = LinearLayoutManager(context)
        adapter= FavoriteAdapter(dataItem,context)
        recycler.adapter = adapter
        mFavorite = FavoritePresenter(this,context?.database)
        mFavorite.fetchMatches()
        refreshLayout.setOnRefreshListener(this)

        return view
    }




}
