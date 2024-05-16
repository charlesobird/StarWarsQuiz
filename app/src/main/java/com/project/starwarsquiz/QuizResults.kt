package com.project.starwarsquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizResults : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_results)

        val playerScore = intent.getIntExtra("CurrentScore", 0)
        val totalScore = intent.getIntExtra("NumberOfQuestions", 0)
        val scoreLabel = findViewById<TextView>(R.id.scoreLabel)

        scoreLabel.text = "$playerScore / $totalScore"

        val congratulationsLabel = findViewById<TextView>(R.id.fullScoreLabel)
        if (playerScore == totalScore) {
            congratulationsLabel.visibility = View.VISIBLE
        } else {
            congratulationsLabel.visibility = View.INVISIBLE
        }
    }

    fun playAgainButtonClick(view: View) {
        val intent = Intent(this@QuizResults, MainActivity::class.java)
        startActivity(intent)
    }
}