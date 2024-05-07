package com.project.starwarsquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun selectCategoryButtonClick(view: View) {
        val intent = Intent(this@MainActivity, MainActivity2::class.java)
        startActivity(intent)
    }
}