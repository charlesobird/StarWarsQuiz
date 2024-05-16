package com.project.starwarsquiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuestionPage : AppCompatActivity() {
    private var quizQuestion: QuizQuestion? = null
    private var questionNumber = 0
    private var score = 0
    private var correctAnswerChosen = false
    private var selectedRadioButton: RadioButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_page)

        val questionLabel = findViewById<TextView>(R.id.labelQuestion)
        val questions = quizQuestions
        questionNumber = intent.getIntExtra("QuestionNo", 1)
        score = intent.getIntExtra("CurrentScore", 0)
        quizQuestion = questions[questionNumber - 1]
        questionLabel.text = quizQuestion!!.question
        val questionNumLabel = findViewById<TextView>(R.id.labelQuestionNum)
        questionNumLabel.text =
            getString(R.string.question_number_template, questionNumber.toString())

        val rbChoiceOne = findViewById<RadioButton>(R.id.rbChoice1)
        val rbChoiceTwo = findViewById<RadioButton>(R.id.rbChoice2)
        val rbChoiceThree = findViewById<RadioButton>(R.id.rbChoice3)
        val rbChoiceFour = findViewById<RadioButton>(R.id.rbChoice4)

        for ((index, button) in arrayOf<RadioButton>(
            rbChoiceOne,
            rbChoiceTwo,
            rbChoiceThree,
            rbChoiceFour
        ).withIndex()) {
            button.text = quizQuestion!!.options[index]
        }

        val radioGroup = findViewById<RadioGroup>(R.id.rgAnswers)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (radioGroup.checkedRadioButtonId.equals(null)) {
                findViewById<Button>(R.id.btnSubmitAnswer).setBackgroundColor(
                    Color.parseColor("#702632")
                )
            } else {
                selectedRadioButton = findViewById<RadioButton>(checkedId)
                findViewById<Button>(R.id.btnSubmitAnswer).setBackgroundColor(Color.parseColor("#58A558"))
            }
        }
    }

    fun goBackToCategorySelection(view: View) {
        val intent = Intent(this@QuestionPage, CategorySelection::class.java)
        startActivity(intent)
    }

    fun submitAnswerButtonClicked(view: View) {
        if (selectedRadioButton == null) return
        val intent = Intent(this@QuestionPage, QuestionResult::class.java)
        if (selectedRadioButton?.text == quizQuestion?.correctAnswer) {
            score++
            correctAnswerChosen = true
        }
        intent.putExtra("QuestionNo", questionNumber)
        intent.putExtra("CurrentScore", score)
        intent.putExtra("CorrectAnswerChosen", correctAnswerChosen)
        startActivity(intent)
    }
}