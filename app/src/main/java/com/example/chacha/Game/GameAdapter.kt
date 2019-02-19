package com.example.chacha.Game

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.chacha.R
import com.example.chacha.model.GameDTO

import kotlinx.android.synthetic.main.item_game.view.*


class GameAdapter(val gameDTO : ArrayList<GameDTO>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gameDTO.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = (holder as CustomViewHolder).itemView

        Glide.with(holder.itemView.context).load(gameDTO[position].img_url).into(viewHolder.imageview_gameimg)
        viewHolder.textview_gametitle.text = gameDTO[position].title

        viewHolder.setOnClickListener {
            val webpage = Uri.parse("https://m.twitch.tv/directory/game/"+gameDTO[position].title)
            val webIntent = Intent(Intent.ACTION_VIEW, webpage)
            holder.itemView.context.startActivity(webIntent)
        }
    }


    inner class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
}