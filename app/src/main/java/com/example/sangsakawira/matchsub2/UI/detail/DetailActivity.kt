package com.example.sangsakawira.matchsub2.UI.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.sangsakawira.matchsub2.Model.EventsItem
import com.example.sangsakawira.matchsub2.Model.db.Favorite
import com.example.sangsakawira.matchsub2.Model.db.database
import com.example.sangsakawira.matchsub2.Model.network.NetworkImage
import com.example.sangsakawira.matchsub2.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.uiThread
import org.json.JSONObject

class DetailActivity : AppCompatActivity(),DetailView,AnkoLogger {

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var id: String = ""
    private lateinit var networkClient: NetworkImage
    private lateinit var mPresenter: DetailPresenter

    override fun getData(data: List<EventsItem>) {
        val data = data[0]

        tv_tanggal.text = data.dateEvent

        showImgDataItems(data.idHomeTeam.toString(),data.idAwayTeam.toString(),networkClient)

        tv_home_team_name.text = data.strHomeTeam
        tv_home_shots.text = data.intHomeShots.toString()
        tv_home_goals.text = data.intHomeScore.toString()
        tv_home_goalkeeper.text = data.strHomeLineupGoalkeeper
        tv_forward_home_team.text = data.strHomeLineupForward
        tv_defense_home_team.text = data.strHomeLineupDefense
        tv_midfield_home_team.text = data.strHomeLineupMidfield
        tv_subtitutes_home_team.text = data.strHomeLineupSubstitutes

        tv_away_team_name.text = data.strAwayTeam
        tv_away_goals.text = data.intAwayScore.toString()
        tv_away_shots.text = data.intAwayShots.toString()
        tv_away_goalkeeper.text = data.strAwayLineupGoalkeeper
        tv_forward_away_team.text = data.strAwayLineupForward
        tv_defense_away_team.text = data.strAwayLineupDefense
        tv_midfield_away_team.text = data.strAwayLineupMidfield
        tv_subtitutes_away_team.text = data.strAwayLineupSubstitutes
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        ll_detail.visibility = View.GONE
        clp_detail_matches.show()
        networkClient = NetworkImage()

        mPresenter = DetailPresenter(this,applicationContext,networkClient)


        val data = intent.getParcelableExtra<EventsItem>(getString(R.string.data))
        val dataFavorite = intent.getStringExtra("dataFavorite")

        if(data!=null){
            id =  data.idEvent.toString()
        }else{
            id = dataFavorite
        }

        info(id)

        mPresenter.fetchMatches(id)

        favoriteState()

    }


    fun showImgDataItems(idHome:String,idAway:String,networkClient: NetworkImage){
        doAsync {
            val jsonObjectHome = JSONObject(networkClient.get("https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=${idHome}"))
            val dataArrayHome = jsonObjectHome.getJSONArray("teams")
            val dataHome = dataArrayHome.getJSONObject(0)

            val jsonObjectAway = JSONObject(networkClient.get("https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=${idAway}"))
            val dataArrayAway = jsonObjectAway.getJSONArray("teams")
            val dataAway = dataArrayAway.getJSONObject(0)

            uiThread {
                Picasso.get().load(dataHome.getString("strTeamBadge")).resize(100,100).into(detail_logo_home_team)
                Picasso.get().load(dataAway.getString("strTeamBadge")).resize(100,100).into(detail_logo_away_team)
                clp_detail_matches.hide()
                ll_detail.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.idEvent to id,
                    Favorite.strHomeTeam to tv_home_team_name.text,
                    Favorite.strAwayTeam to tv_away_team_name.text,
                    Favorite.dateEvent to  tv_tanggal.text ,
                    Favorite.intHomeScore to tv_home_goals.text,
                    Favorite.intAwayScore to tv_away_goals.text
                )
                Toast.makeText(this@DetailActivity,getString(R.string.added_favorite),Toast.LENGTH_SHORT).show()
            }
        } catch (e: SQLiteConstraintException){
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(idEvent = {id})",
                    "id" to id)
                Toast.makeText(this@DetailActivity,getString(R.string.moved_favorite),Toast.LENGTH_SHORT).show()
            }
        } catch (e: SQLiteConstraintException){
        }
    }

    private fun setFavorite() {
        if (isFavorite){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        }
        else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
        }

    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(idEvent = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}
