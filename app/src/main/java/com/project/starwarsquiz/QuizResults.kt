package com.project.starwarsquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizResults : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_results)

        // get the relevant intents
        val playerScore = intent.getIntExtra("CurrentScore", 0)
        val totalScore = intent.getIntExtra("NumberOfQuestions", 0)
        // change the text of the score label to say what the user got out of the total score
        val scoreLabel = findViewById<TextView>(R.id.scoreLabel)
        scoreLabel.text = "$playerScore / $totalScore"

        // if they got max, display a congratulations for getting max score
        val congratulationsLabel = findViewById<TextView>(R.id.fullScoreLabel)
        if (playerScore == totalScore) {
            congratulationsLabel.visibility = View.VISIBLE
        } else {
            congratulationsLabel.visibility = View.INVISIBLE
        }
    }

    // restart the quiz
    fun playAgainButtonClick(view: View) {
        view.isHapticFeedbackEnabled = true
        view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
        val intent = Intent(this@QuizResults, MainActivity::class.java)
        startActivity(intent)
    }
}