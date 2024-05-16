package com.project.starwarsquiz

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuestionResult : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_result)

        val correctAnswerIcon = R.drawable.check_circle
        val incorrectAnswerIcon = R.drawable.cross_circle

        val score = intent.getIntExtra("CurrentScore", 0)
        val scoreLabel = findViewById<TextView>(R.id.labelCurrentScore)
        scoreLabel.text = "Your Score: $score / ${quizQuestions.size}"

        var questionNumber = intent.getIntExtra("QuestionNo", 1)
        val questionNoLabel = findViewById<TextView>(R.id.labelQuestionNum)
        questionNoLabel.text = "Question $questionNumber"
        val quizQuestion = quizQuestions[questionNumber - 1]
        // quizQuestions is a zero-index array/list
        val questionLabel = findViewById<TextView>(R.id.labelQuestion)
        questionLabel.text = quizQuestion.question

        val correctAnswer = intent.getBooleanExtra("CorrectAnswerChosen", false)
        val correctAnswerLabel = findViewById<TextView>(R.id.correctAnswerLabel)
        val questionIcon = findViewById<ImageView>(R.id.answerResultIcon)
        if (correctAnswer) {
            questionIcon.setImageResource(correctAnswerIcon)
            correctAnswerLabel.setTextColor(Color.GREEN)
        } else {
            questionIcon.setImageResource(incorrectAnswerIcon)
            correctAnswerLabel.setTextColor(Color.RED)
        }
        correctAnswerLabel.text = "The Correct Answer was: ${quizQuestion.correctAnswer}"

        val nextQuestionButton = findViewById<Button>(R.id.btnNextQuestion)
        nextQuestionButton.setOnClickListener {
            questionNumber++
            val intent: Intent
            if (questionNumber - 1 == quizQuestions.size) {
                intent = Intent(this@QuestionResult, QuizResults::class.java)
                intent.putExtra("NumberOfQuestions", quizQuestions.size)
            } else {
                intent = Intent(this@QuestionResult, QuestionPage::class.java)
                intent.putExtra("QuestionNo", questionNumber)
            }
            intent.putExtra("CurrentScore", score)
            startActivity(intent)
        }
    }

    fun goBackToCategorySelection(view: View) {
        val intent = Intent(this@QuestionResult, CategorySelection::class.java)
        startActivity(intent)
    }
}