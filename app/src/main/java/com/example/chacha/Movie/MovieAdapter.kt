package com.example.chacha.Movie

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.chacha.R
import com.example.chacha.model.MovieDTO
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(val movieDTO : ArrayList<MovieDTO>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieDTO.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = (holder as CustomViewHolder).itemView

        Glide.with(holder.itemView.context).load(movieDTO[position].img_url).into(viewHolder.imageview_movieimg)
        viewHolder.textview_movietitle.text = movieDTO[position].title
        viewHolder.textview_movierelease.text = movieDTO[position].release
        viewHolder.textview_moviedirector.text = movieDTO[position].director
        viewHolder.textview_movierate.text = movieDTO[position].rate

        viewHolder.setOnClickListener {
            val webpage = Uri.parse("https://movie.naver.com"+movieDTO[position].detail_link)
            val webIntent = Intent(Intent.ACTION_VIEW, webpage)
            holder.itemView.context.startActivity(webIntent)
        }
    }

    inner class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
}