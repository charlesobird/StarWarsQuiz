package com.project.starwarsquiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuestionResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_result)

        // These are the icons used to show correct or incorrect question results
        val correctAnswerIcon = R.drawable.check_circle
        val incorrectAnswerIcon = R.drawable.cross_circle
        // Score Label
        val score = intent.getIntExtra("CurrentScore", 0)
        val scoreLabel = findViewById<TextView>(R.id.labelCurrentScore)
        scoreLabel.text = getString(
            R.string.score_label_text_template, score.toString(), quizQuestions.size
                .toString()
        )

        // Question Labels
        var questionNumber = intent.getIntExtra("QuestionNo", 1)
        val questionNoLabel = findViewById<TextView>(R.id.labelQuestionNum)
        questionNoLabel.text =
            getString(R.string.question_number_label_template, questionNumber.toString())
        // finds the current question, - 1 is used because quizQuestions is a zero-index array/list
        val quizQuestion = quizQuestions[questionNumber - 1]
        val questionLabel = findViewById<TextView>(R.id.labelQuestion)
        questionLabel.text = quizQuestion.question

        // Correct Answer Icon and Label
        val correctAnswer = intent.getBooleanExtra("CorrectAnswerChosen", false)
        val correctAnswerLabel = findViewById<TextView>(R.id.correctAnswerLabel)
        val questionIcon = findViewById<ImageView>(R.id.answerResultIcon)
        // Decides whether the icon is a check or cross and if it is green or red
        if (correctAnswer) {
            questionIcon.setImageResource(correctAnswerIcon)
            correctAnswerLabel.setTextColor(Color.GREEN)
        } else {
            questionIcon.setImageResource(incorrectAnswerIcon)
            correctAnswerLabel.setTextColor(Color.RED)
        }
        correctAnswerLabel.text =
            getString(R.string.correct_answer_template, quizQuestion.correctAnswer)

        // NextQuestion button setup
        val nextQuestionButton = findViewById<Button>(R.id.btnNextQuestion)
        nextQuestionButton.setOnClickListener {
            // Haptic Feedback (API Version 30+)
            it.isHapticFeedbackEnabled = true
            it.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
            // Increments question number so that the next question can be found
            questionNumber++
            val intent: Intent
            // Checks to see if the quiz is finished
            // If finished, show QuizResults page which final score
            if (questionNumber - 1 == quizQuestions.size) {
                intent = Intent(this@QuestionResult, QuizResults::class.java)
                // For displaying score as "CurrentScore / NumberOfQuestions"
                intent.putExtra("NumberOfQuestions", quizQuestions.size)
            } else {
                intent = Intent(this@QuestionResult, QuestionPage::class.java)
                intent.putExtra("QuestionNo", questionNumber)
            }
            // Both pages above need the score, so is added to the intent here
            intent.putExtra("CurrentScore", score)
            startActivity(intent)
        }
    }

    // Takes the user back to the CategorySelection page to restart the quiz
    fun goBackToCategorySelection(view: View) {
        view.isHapticFeedbackEnabled = true
        view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
        val intent = Intent(this@QuestionResult, CategorySelection::class.java)
        startActivity(intent)
    }
}