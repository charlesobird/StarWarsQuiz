package com.project.starwarsquiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class CategorySelection : AppCompatActivity() {
    private var selectedCategory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_selection)

        val radioGroup = findViewById<RadioGroup>(R.id.rgAnswers)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (!radioGroup.checkedRadioButtonId.equals(null)) {
                selectedCategory = findViewById<RadioButton>(checkedId).text.toString()
            }
        }
    }

    fun beginQuizButtonClick(view: View) {
        val intent = Intent(this@CategorySelection, QuestionPage::class.java)
        findViewById<RadioGroup>(R.id.rgAnswers)

        CoroutineScope(Dispatchers.IO).launch {
            val questionGenerators = QuestionGenerators()
            if (selectedCategory == resources.getString(R.string.radio_planets)) {
                val planets: List<Planet> =
                    service.planets().awaitResponse().body()?.results ?: emptyList()
                questionGenerators.generatePlanetQuizQuestions(planets) // populates a
                // variable
                // within the QuestionGenerators class that stores the questions
            } else if (selectedCategory == resources.getString(R.string.radio_characters)) {
                val characters: List<Character> =
                    service.characters().awaitResponse().body()?.results ?: emptyList()
                questionGenerators.generateCharacterQuizQuestions(characters) // populates a variable
                // within the QuestionGenerators class that stores the questions
            } else if (selectedCategory == resources.getString(R.string.radio_ships)) {
                val ships: List<Ship> =
                    service.ships().awaitResponse().body()?.results ?: emptyList()
                questionGenerators.generateShipQuizQuestions(ships)
            } else if (selectedCategory == resources.getString(R.string.radio_species)) {
                val speciesList: List<Species> =
                    service.species().awaitResponse().body()?.results ?: emptyList()
                questionGenerators.generateSpeciesQuizQuestions(speciesList) // populates a variable
                // within the QuestionGenerators class that stores the questions
            } else {
                throw Exception("Missing category!")
            }

        }.invokeOnCompletion {
            intent.putExtra("Questions", quizQuestions as ArrayList<QuizQuestion>)
            startActivity(intent)
        }

    }
}
