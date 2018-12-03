package com.example.sangsakawira.matchsub2.UI.matches

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.ContentLoadingProgressBar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sangsakawira.matchsub2.Model.EventsItem
import com.example.sangsakawira.matchsub2.R
import com.example.sangsakawira.matchsub2.UI.matches.adapter.MatchesAdapter
import kotlinx.android.synthetic.main.fragment_matches.*
import org.jetbrains.anko.find

class MatchesFragment:Fragment(),MatchesView{
    private val mPresenter by lazy { MatchesPresenter(this,context) }

    private var mType = context?.getString(R.string.next)

    fun setType(type: String) {
        mType = type
    }

    override fun showLoading() {
        loading.show()
        recyclerView.visibility = View.GONE
        tv_matches_placeholder.visibility = View.GONE
    }

    override fun hideLoading() {
        loading.hide()
    }

    override fun showPlaceholder() {
        hideLoading()
        tv_matches_placeholder.visibility = View.VISIBLE
    }

    override fun hidePlaceholder() {
        tv_matches_placeholder.visibility = View.GONE
    }

    override fun showMatches(matches: List<EventsItem>) {
        hideLoading()

        recyclerView.visibility = View.VISIBLE
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MatchesAdapter(matches,context)
//        with(rv_matches) {
//            visibility = View.VISIBLE
//            layoutManager = LinearLayoutManager(activity)
//            adapter = MatchesAdapter(matches,context)
//        }
    }

    private lateinit var loading:ContentLoadingProgressBar
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.fragment_matches, container, false)
        loading = view.find(R.id.clp_matches)
        recyclerView = view.find(R.id.rv_matches)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mPresenter.fetchMatches(mType)
    }

}