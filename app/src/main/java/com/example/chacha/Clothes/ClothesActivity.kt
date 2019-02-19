package com.example.chacha.Clothes

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.chacha.R
import com.example.chacha.model.IssueDTO
import kotlinx.android.synthetic.main.activity_clothes.*
import kotlinx.android.synthetic.main.snippet_top_clothestoolbar.*
import org.jsoup.Jsoup
import java.io.IOException

class ClothesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clothes)
        Description().execute()

        ivBackArrow.setOnClickListener {
            finish()
        }
    }

    inner class Description : AsyncTask<Void, Void, Void>() {

        var clothesDTO : ArrayList<IssueDTO> = ArrayList()

        override fun onPostExecute(result: Void?) {
            clothes_recyclerview.adapter = ClothesAdapter(clothesDTO)
            clothes_recyclerview.layoutManager = LinearLayoutManager(this@ClothesActivity)
            progress_bar.visibility = View.GONE
        }

        override fun doInBackground(vararg params: Void?): Void? {
            try {
                val doc = Jsoup.connect("https://store.musinsa.com/app/usr/search_ranking").get()
                val mElementDataSize = doc.select("ol[class=sranking_list]").select("li")

                for (elem in mElementDataSize) {
                    val my_title = elem.select("a").attr("title")
                    val my_link = elem.select("a").attr("href")
                    clothesDTO.add(IssueDTO(my_title,my_link))
                }


            }catch (e : IOException){
                e.printStackTrace()
            }
            return null
        }

    }
}