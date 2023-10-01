package com.example.tictactoe

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var start = findViewById<Button>(R.id.startGameButton)
        var ai = findViewById<Button>(R.id.Ai)

        start.setOnClickListener {
            var t = Intent(this, TwoPlayerActivity::class.java)
            startActivity(t)
        }
            ai.setOnClickListener {
            var t = Intent(this, AiActivity::class.java)
            startActivity(t)
        }

    }
}