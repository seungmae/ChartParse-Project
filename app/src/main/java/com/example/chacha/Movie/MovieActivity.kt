package com.example.chacha.Movie

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.example.chacha.R
import com.example.chacha.model.MovieDTO
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.snippet_top_movietoolbar.*
import org.jsoup.Jsoup

import java.io.IOException

class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        Description().execute()

        ivBackArrow.setOnClickListener {
            finish()
        }
    }

    inner class Description : AsyncTask<Void, Void, Void>() {

        val movieDTO : ArrayList<MovieDTO> = ArrayList()

        override fun onPostExecute(result: Void?) {
            movie_recyclerview.adapter = MovieAdapter(movieDTO)
            movie_recyclerview.layoutManager = LinearLayoutManager(this@MovieActivity)

            progress_bar.visibility = View.GONE
        }

        override fun doInBackground(vararg params: Void?): Void? {
            try {
                val doc = Jsoup.connect("https://movie.naver.com/movie/running/current.nhn").get()
                val mElementDataSize = doc.select("ul[class=lst_detail_t1]").select("li")

                for (elem in mElementDataSize) {
                    val my_title = elem.select("dt[class=tit] a").text()
                    val my_link = elem.select("div[class=thumb] a").attr("href")
                    val my_imgUrl = elem.select("div[class=thumb] a img").attr("src")

                    val rElem = elem.select("dl[class=info_txt1] dt").next().first()
                    val my_release = rElem.select("dd").text()

                    val dElem = elem.select("dt[class=tit_t2]").next().first()
                    val my_director = "감독 : " + dElem.select("a").text()

                    val my_rate = "예매율 : " + elem.select("div[class=star_t1 b_star]").text()
                    movieDTO.add(MovieDTO(my_title, my_imgUrl, my_link, my_director, my_release, my_rate))

//                    for(s in movieDTO){
//                        Log.d("TAG", "String : " + s)
//                    }
                }
            }catch (e : IOException){
                e.printStackTrace()
            }
            return null
        }

    }
}