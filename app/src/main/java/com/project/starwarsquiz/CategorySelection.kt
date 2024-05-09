package com.project.starwarsquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup

class CategorySelection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_selection)
    }

    fun beginQuizButtonClick(view: View) {
        val intent = Intent(this@CategorySelection, QuestionPage::class.java)
        intent.putExtra("Category",findViewById<RadioButton>(findViewById<RadioGroup>(R.id.rgChoices).getCheckedRadioButtonId()).text)
        startActivity(intent)
    }
}