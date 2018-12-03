package com.example.sangsakawira.matchsub2.UI.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.sangsakawira.matchsub2.R
import com.example.sangsakawira.matchsub2.UI.favoritefragment.FavoriteFragment
import com.example.sangsakawira.matchsub2.UI.matches.MatchesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                startFragment(getString(R.string.next))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                startFragment(getString(R.string.past))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                startFragment(R.string.activity_favorite.toString())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            startFragment(getString(R.string.past).toString())
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    fun startFragment(type:String){

        if (type.equals(R.string.activity_favorite.toString())){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_matches, FavoriteFragment())
                .commit()
        }
        else{
            fragment_matches.visibility = View.VISIBLE
            val nextFragment = MatchesFragment().apply { setType(type) }

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_matches, nextFragment)
                .commit()
        }

    }
}
