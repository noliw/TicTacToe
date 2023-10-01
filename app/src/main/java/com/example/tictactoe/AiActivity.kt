package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.example.tictactoe.databinding.ActivityAiBinding

class AiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAiBinding
    private var turn = "1"
    private var isDialogShowing = false

    private var moveCounter = 0   // Counter to track number of moves
    private var isGameActive = true
    private val allImageViews by lazy {
        listOf (
            binding.imageView, binding.imageView2, binding.imageView3,
            binding.imageView4, binding.imageView5, binding.imageView6,
            binding.imageView7, binding.imageView8, binding.imageView9
        )
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playAgainBtn.setOnClickListener { reset() }

        // Set up click listeners for all ImageViews
        for (imageView in allImageViews) {
            imageView.setOnClickListener {
                buttonClick(it as ImageView)
                win()
            }
        }
    }

    fun ai() {
        if (!isGameActive) return  // Don't play if game is over

        // Introduce a delay using Handler
        Handler(Looper.getMainLooper()).postDelayed({
            val emptyCells = allImageViews.filter { it.tag == null }
            if (emptyCells.isNotEmpty()) {
                val randomCell = emptyCells.random()
                randomCell.setImageResource(R.drawable.o)
                randomCell.tag = "2"
                moveCounter++
                win()// Check if AI won after making its move
            }  // Delay of 1 second (1000 milliseconds
        }, 500)
    }


    private fun buttonClick(btn: ImageView) {
        // Check if ImageView is empty by its tag
        if (isGameActive && btn.tag == null) {
            btn.setImageResource(R.drawable.x)
            btn.tag = "1"
            moveCounter++
            win()

            // Only let the AI make a move if the game is still active AFTER checking for player 1 win
            if (isGameActive) {
                ai()  // AI makes its move after Player 1
            }
        }
    }


    fun win() {
        val win = setOf(
            listOf(binding.imageView, binding.imageView2, binding.imageView3),
            listOf(binding.imageView4, binding.imageView5, binding.imageView6),
            listOf(binding.imageView7, binding.imageView8, binding.imageView9),
            listOf(binding.imageView, binding.imageView4, binding.imageView7),
            listOf(binding.imageView2, binding.imageView5, binding.imageView8),
            listOf(binding.imageView3, binding.imageView6, binding.imageView9),
            listOf(binding.imageView, binding.imageView5, binding.imageView9),
            listOf(binding.imageView3, binding.imageView5, binding.imageView7)
        )

        for (condition in win) {
            if (condition.all { it.tag == "1" }) {
                isGameActive = false
                showDialog("Player 1 has won", "CONGRATULATIONS PLAYER 1")
                return
            }
            if (condition.all { it.tag == "2" }) {
                isGameActive = false
                showDialog("computer has won", "YOU LOST ")
                return
            }
        }

        if (moveCounter == 9 && isGameActive) {
            showDialog("It's a tie!", "Aweeeee")
            isGameActive = false
        }
    }

    private fun showDialog(message: String, title: String) {
        if (isDialogShowing) return

        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage(message)
            .setTitle(title)
            .setCancelable(false)
            .setPositiveButton("Go back") { dialog, _ ->
                isDialogShowing = false
                dialog.dismiss()
            }
            .setNegativeButton("Reset") { dialog, _ ->
                reset()
                isDialogShowing = false
                dialog.dismiss()
            }
            .show()

        isDialogShowing = true
    }




    private fun reset() {
        for (imageView in allImageViews) {
            imageView.tag = null
            imageView.setImageResource(0)  // Clear the image
        }
        turn = "1"
        moveCounter = 0  // Reset move counter
        isGameActive = true
    }
}