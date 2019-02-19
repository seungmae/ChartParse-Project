package com.example.chacha.Music

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.chacha.R
import com.example.chacha.model.MusicDTO
import kotlinx.android.synthetic.main.activity_music.*
import kotlinx.android.synthetic.main.snippet_top_musictoolbar.*
import org.jsoup.Jsoup

import java.io.IOException

class MusicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        Description().execute()

        ivBackArrow.setOnClickListener {
            finish()
        }
    }

    inner class Description : AsyncTask<Void, Void, Void>() {

        val musicDTO : ArrayList<MusicDTO> = ArrayList()

        override fun onPostExecute(result: Void?) {
            music_recyclerview.adapter = MusicAdapter(musicDTO)
            music_recyclerview.layoutManager = LinearLayoutManager(this@MusicActivity)

            progress_bar.visibility = View.GONE
        }

        override fun doInBackground(vararg params: Void?): Void? {
            try {
                val doc = Jsoup.connect("https://www.melon.com/chart/").get()
                val mElementDataSize = doc.select("div[class=service_list_song type02 d_song_list]").select("tr")

                for (elem in mElementDataSize) {
                    val my_title = elem.select("div[class=ellipsis rank01] a").text()
                    val my_artist = elem.select("span[class=checkEllipsis] a").text()
                    val my_imgUrl = elem.select("div[class=wrap] a img").attr("src")

                    musicDTO.add(MusicDTO(my_title, my_artist, my_imgUrl))
                }

            }catch (e : IOException){
                e.printStackTrace()
            }
            return null
        }

    }
}