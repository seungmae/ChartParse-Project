package com.example.chacha.Home

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.chacha.Clothes.ClothesActivity
import com.example.chacha.Game.GameActivity
import com.example.chacha.Issue.IssueActivity
import com.example.chacha.Movie.MovieActivity
import com.example.chacha.Music.MusicActivity
import com.example.chacha.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, SplashActivity::class.java))

        music_chart.setOnClickListener {
            startActivity(Intent(this, MusicActivity::class.java))
        }
        game_chart.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }
        movie_chart.setOnClickListener {
            startActivity(Intent(this, MovieActivity::class.java))
        }
        issue_chart.setOnClickListener {
            startActivity(Intent(this, IssueActivity::class.java))
        }
        clothes_chart.setOnClickListener {
            startActivity(Intent(this, ClothesActivity::class.java))
        }
    }
}

