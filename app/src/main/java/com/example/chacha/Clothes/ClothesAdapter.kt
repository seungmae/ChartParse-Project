package com.example.chacha.Clothes

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chacha.R
import com.example.chacha.model.IssueDTO
import kotlinx.android.synthetic.main.item_issue.view.*

class ClothesAdapter(val issueDTO : ArrayList<IssueDTO>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_issue, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return issueDTO.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = (holder as CustomViewHolder).itemView

        viewHolder.textview_issue.text = issueDTO[position].title
        viewHolder.rankcnt.text = (position+1).toString()
        viewHolder.setOnClickListener {
            val webpage = Uri.parse("https://store.musinsa.com/app/product/search?type=&q="+issueDTO[position].title)
            val webIntent = Intent(Intent.ACTION_VIEW, webpage)
            holder.itemView.context.startActivity(webIntent)
        }
    }

    inner class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
}