package com.example.chacha.Music

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.chacha.R
import com.example.chacha.model.MusicDTO
import kotlinx.android.synthetic.main.item_music.view.*

class MusicAdapter(val musicDTO : ArrayList<MusicDTO>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_music, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return musicDTO.size-1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = (holder as CustomViewHolder).itemView

        Glide.with(holder.itemView.context).load(musicDTO[position+1].img_url).into(viewHolder.imageview_musicimg)
        viewHolder.textview_musictitle.text = musicDTO[position+1].title
        viewHolder.textview_musicartist.text = musicDTO[position+1].artist
        viewHolder.rankcnt.text = (position+1).toString()

        viewHolder.setOnClickListener {
            val webpage = Uri.parse("https://www.youtube.com/results?search_query="+ musicDTO[position+1].title + musicDTO[position+1].artist)
            val webIntent = Intent(Intent.ACTION_VIEW, webpage)
            holder.itemView.context.startActivity(webIntent)
        }
    }

    inner class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
}