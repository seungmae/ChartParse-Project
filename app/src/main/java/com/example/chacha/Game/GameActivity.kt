package com.example.chacha.Game

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.View
import com.example.chacha.R
import com.example.chacha.model.GameDTO
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.snippet_top_gametoolbar.*
import org.jsoup.Jsoup
import java.io.IOException

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        Description().execute()

        ivBackArrow.setOnClickListener {
            finish()
        }
    }

    inner class Description : AsyncTask<Void, Void, Void>() {

        val gameDTO : ArrayList<GameDTO> = ArrayList()
        var cnt = 0
        override fun onPostExecute(result: Void?) {
            game_recyclerview.adapter = GameAdapter(gameDTO)
            game_recyclerview.layoutManager = GridLayoutManager(this@GameActivity,2)

            progress_bar.visibility = View.GONE
        }

        override fun doInBackground(vararg params: Void?): Void? {
            try {
                val doc = Jsoup.connect("https://m.twitch.tv/directory").get()
                val mElementDataSize = doc.select("div[class=game-list]").select("a")

                for (elem in mElementDataSize) {
                    val my_title = elem.select("div[class=tw-aspect tw-aspect--align-top] img").attr("alt")
                    val my_imgUrl = elem.select("div[class=tw-aspect tw-aspect--align-top] img").attr("src")

                    gameDTO.add(GameDTO(my_title,my_imgUrl))
                }
                for(s in gameDTO){
                    Log.d("Tag", ""+s)
                }
            }catch (e : IOException){
                e.printStackTrace()
            }
            return null
        }

    }
}