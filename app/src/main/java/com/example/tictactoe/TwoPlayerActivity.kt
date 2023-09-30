package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.tictactoe.databinding.ActivityMainBinding
import com.example.tictactoe.databinding.ActivityTwoPlayerBinding

class TwoPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTwoPlayerBinding
    private var turn = "1"
    private var moveCounter = 0   // Counter to track number of moves
    private var isGameActive = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTwoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playAgainBtn.setOnClickListener { reset() }

        val allImageViews = listOf(
            binding.imageView, binding.imageView2, binding.imageView3,
            binding.imageView4, binding.imageView5, binding.imageView6,
            binding.imageView7, binding.imageView8, binding.imageView9
        )

        // Set up click listeners for all ImageViews
        for (imageView in allImageViews) {
            imageView.setOnClickListener {
                buttonClick(it as ImageView)
                win()
            }
        }
    }

    private fun buttonClick(btn: ImageView) {
        // Check if ImageView is empty by its tag
        if (isGameActive && btn.tag == null ) {
            turn = if (turn == "1") {
                btn.setImageResource(R.drawable.x)
                btn.tag = "1"
                moveCounter++  // Increment the move counter
                "2"
            } else {
                btn.setImageResource(R.drawable.o)
                btn.tag = "2"
                moveCounter++  // Increment the move counter
                "1"
            }
        }
    }

    fun win() {
        // Player 1 win conditions
        val p1Win = setOf(
            listOf(binding.imageView, binding.imageView2, binding.imageView3),
            listOf(binding.imageView4, binding.imageView5, binding.imageView6),
            listOf(binding.imageView7, binding.imageView8, binding.imageView9),
            listOf(binding.imageView, binding.imageView4, binding.imageView7),
            listOf(binding.imageView2, binding.imageView5, binding.imageView8),
            listOf(binding.imageView3, binding.imageView6, binding.imageView9),
            listOf(binding.imageView, binding.imageView5, binding.imageView9),
            listOf(binding.imageView3, binding.imageView5, binding.imageView7),
            // ... add the other win conditions for Player 1
        )

        // Player 2 win conditions
        val p2Win = setOf(
            listOf(binding.imageView, binding.imageView2, binding.imageView3),
            listOf(binding.imageView4, binding.imageView5, binding.imageView6),
            listOf(binding.imageView7, binding.imageView8, binding.imageView9),
            listOf(binding.imageView, binding.imageView4, binding.imageView7),
            listOf(binding.imageView2, binding.imageView5, binding.imageView8),
            listOf(binding.imageView3, binding.imageView6, binding.imageView9),
            listOf(binding.imageView, binding.imageView5, binding.imageView9),
            listOf(binding.imageView3, binding.imageView5, binding.imageView7),

            // ... add the other win conditions for Player 2
        )

        for (condition in p1Win) {
            if (condition.all { it.tag == "1" }) {
                showDialog("Player 1 has won", "CONGRATULATIONS PLAYER 1")
                isGameActive = false
                return
            }
        }

        for (condition in p2Win) {
            if (condition.all { it.tag == "2" }) {
                showDialog("Player 2 has won", "CONGRATULATIONS PLAYER 2")
                isGameActive = false
                return
            }
        }

        // Check for a tie if all cells are occupied
        if (moveCounter == 9) {
            showDialog("It's a tie!", "Aweeeee")
            isGameActive = false
        }
    }

    private fun showDialog(message: String, title: String) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage(message)
            .setTitle(title)
            .setCancelable(true)
            .setPositiveButton("Go back") { dialog, _ ->
                dialog.cancel()
            }
            .setNegativeButton("Reset") { _, _ ->
                reset()
            }
            .show()  // Show the dialog
    }

    private fun reset() {
        isGameActive = true
        val allImageViews = listOf(
            binding.imageView, binding.imageView2, binding.imageView3,
            binding.imageView4, binding.imageView5, binding.imageView6,
            binding.imageView7, binding.imageView8, binding.imageView9
        )

        for (imageView in allImageViews) {
            imageView.tag = null
            imageView.setImageResource(0)  // Clear the image
        }
        turn = "1"
        moveCounter = 0  // Reset move counter
        isGameActive = true
    }
}