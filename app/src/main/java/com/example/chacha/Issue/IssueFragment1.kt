package com.example.chacha.Issue

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chacha.R
import com.example.chacha.model.IssueDTO
import kotlinx.android.synthetic.main.issue_fragment1.*
import kotlinx.android.synthetic.main.item_issue.view.*
import org.jsoup.Jsoup
import java.io.IOException

class IssueFragment1 : Fragment() {

    var mainView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mainView = inflater.inflate(R.layout.issue_fragment1, container, false)
        Description().execute()
        return mainView
    }

    inner class Description : AsyncTask<Void, Void, Void>() {

        val issueDTO : ArrayList<IssueDTO> = ArrayList()

        override fun onPostExecute(result: Void?) {
            fragment_recyclerview.adapter = IssueAdapter(issueDTO)
            fragment_recyclerview.layoutManager = LinearLayoutManager(activity)

        }

        override fun doInBackground(vararg params: Void?): Void? {
            try {
                val doc = Jsoup.connect("https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=%EB%89%B4%EC%8A%A4%ED%86%A0%ED%94%BD").get()
                val mElementDataSize = doc.select("ol[class=lst_realtime_srch _tab_area]").select("li")

                for (elem in mElementDataSize) {
                    val my_title = elem.select("span[class=tit]").text()
                    val my_link = elem.select("a").attr("href")

                    issueDTO.add(IssueDTO(my_title,my_link))
                }

            }catch (e : IOException){
                e.printStackTrace()
            }
            return null
        }

    }
}

class IssueAdapter(val issueDTO : ArrayList<IssueDTO>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_issue, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return issueDTO.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = (holder as CustomViewHolder).itemView
        viewHolder.rankcnt.text = (position+1).toString()
        viewHolder.textview_issue.text = issueDTO[position].title

        viewHolder.setOnClickListener {
            val webpage = Uri.parse("https:"+issueDTO[position].link_detail)
            val webIntent = Intent(Intent.ACTION_VIEW, webpage)
            holder.itemView.context.startActivity(webIntent)
        }
    }

    inner class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
}
