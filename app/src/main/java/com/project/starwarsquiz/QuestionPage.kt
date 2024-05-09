package com.project.starwarsquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class QuestionPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_page)
        var questionLabel = findViewById<TextView>(R.id.labelQuestion)
        questionLabel.text = intent.getStringExtra("Category")
    }
}