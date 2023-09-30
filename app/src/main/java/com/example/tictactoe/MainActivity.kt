package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var start = findViewById<Button>(R.id.startGameButton)

        start.setOnClickListener {
            var t = Intent(this, TwoPlayerActivity::class.java)
            startActivity(t)
        }

    }
}