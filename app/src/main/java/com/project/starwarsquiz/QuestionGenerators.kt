package com.project.starwarsquiz

import android.os.Parcelable
import android.util.Log
import kotlinx.parcelize.Parcelize
import retrofit2.awaitResponse

@Parcelize
data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswer: String
) : Parcelable

var quizQuestions = mutableListOf<QuizQuestion>()

class QuestionGenerators {
    fun generatePlanetQuizQuestions(planets: List<Planet>): List<QuizQuestion> {
        quizQuestions.clear()
        val shuffledPlanets = planets.shuffled()
        for (planet in shuffledPlanets) {
            // Shuffle the list of planets to randomize options
            // Create a list of attributes to include in the quiz questions
            val climates = arrayOf(
                "arid",
                "temperate",
                "temperate, tropical",
                "frozen",
                "murky"
            )

// Shuffle the climates to randomize question order
            val shuffledClimates = climates.toList().shuffled()

// Ensure that the correct answer is in the first position
            val options = mutableListOf(shuffledClimates[0])

// Add three other random climate values as options
            for (otherClimate in shuffledClimates) {
                if (options.size == 4) break
                if (otherClimate != options.first()) {
                    options.add(otherClimate)
                }
            }

// Shuffle the options again to mix them up
            options.shuffle()

// Create the quiz question with the climate as the question text
            val quizQuestion = QuizQuestion(
                question = "What is the climate of ${planet.name}?",
                options = options.filter { it.isNotBlank() }, // Filter out blank options
                correctAnswer = shuffledClimates.first()
            )
            quizQuestions.add(quizQuestion)
        }

        return quizQuestions
    }

    suspend fun generateCharacterQuizQuestions(characters: List<Character>): List<QuizQuestion> {
        quizQuestions.clear()
        // Shuffle the list of planets to randomize options
        val shuffledCharacters = characters.shuffled()
        for (character in shuffledCharacters) {
            // Create a list of attributes to include in the quiz questions

            var homeWorld = ""
            homeWorld = service.planet(character.homeworld.toString()).awaitResponse().body()?.name
                .toString()
            val attributes = listOf(
                // Shuffle the list of planets to randomize options
                "Height" to character.height,
                "Hair Colour" to character.hairColor,
                "Skin Colour" to character.skinColor,
                "Eye Colour" to character.eyeColor,
                "Gender" to character.gender,
                "Home-world" to homeWorld
            )

            val shuffledAttributes = attributes.shuffled()

            // Ensure that the correct answer is in the first position
            val options = mutableListOf(shuffledAttributes.first()?.second ?: "")

            // Add three other random attribute values as options
            for (otherAttribute in shuffledAttributes) {
                if (options.size == 4) break
                if (otherAttribute.second != options.first()) {
                    otherAttribute.second?.let { options.add(it) }
                }
            }

            // Shuffle the options again to mix them up
            options.shuffle()

            // Create the quiz question with the attribute as the question text
            val quizQuestion = QuizQuestion(
                question = "What is the ${shuffledAttributes.first()?.first} of ${character.name}?",
                options = options.filter { it.isNotBlank() }, // Filter out blank options
                correctAnswer = shuffledAttributes?.first()?.second ?: ""
            )
            Log.println(
                Log.INFO,
                "CHARACTER_QUIZ_QUESTION",
                quizQuestion.toString()
            )
            quizQuestions.add(quizQuestion)


        }
        return quizQuestions
    }

    fun generateSpeciesQuizQuestions(speciesList: List<Species>): List<QuizQuestion> {
        quizQuestions.clear()
        // Shuffle the list of planets to randomize options
        val shuffledSpecies = speciesList.shuffled()
        for (species in shuffledSpecies) {
            // Create a list of attributes to include in the quiz questions

            val attributes = listOf(
                // Shuffle the list of planets to randomize options
                "Average Height" to species.averageHeight,
                "Hair Colours" to species.hairColors,
                "Skin Colours" to species.skinColors,
                "Eye Colours" to species.eyeColors,
                "Average Lifespan" to species.averageLifespan,
                "Classification" to species.classification
            )
            val shuffledAttributes = attributes.shuffled()

            // Ensure that the correct answer is in the first position
            val options = mutableListOf(shuffledAttributes.first()?.second ?: "")

            // Add three other random attribute values as options
            for (otherAttribute in shuffledAttributes) {
                if (options.size == 4) break
                if (otherAttribute.second != options.first()) {
                    otherAttribute.second?.let { options.add(it) }
                }
            }

            // Shuffle the options again to mix them up
            options.shuffle()

            // Create the quiz question with the attribute as the question text
            val quizQuestion = QuizQuestion(
                question = "What is the ${shuffledAttributes.first().first} of ${species.name}?",
                options = options.filter { it.isNotBlank() }, // Filter out blank options
                correctAnswer = shuffledAttributes?.first()?.second ?: ""
            )
            quizQuestions.add(quizQuestion)
        }
        return quizQuestions
    }

    fun generateShipQuizQuestions(ships: List<Ship>): List<QuizQuestion> {
        quizQuestions.clear()
        // Shuffle the list of planets to randomize options
        val shuffledShips = ships.shuffled()
        for (ship in shuffledShips) {
            // Create a list of attributes to include in the quiz questions

            val attributes = listOf(
                // Shuffle the list of planets to randomize options
                "Model" to ship.model,
                "Starship Class" to ship.starshipClass,
                "Manufacturer" to ship.manufacturer,
                "Length" to ship.length
            )
            val shuffledAttributes = attributes.shuffled()

            // Ensure that the correct answer is in the first position
            val options = mutableListOf(shuffledAttributes.first().second ?: "")

            // Add three other random attribute values as options
            for (otherAttribute in shuffledAttributes) {
                if (options.size == 4) break
                if (otherAttribute.second != options.first()) {
                    otherAttribute.second?.let { options.add(it) }
                }
            }

            // Shuffle the options again to mix them up
            options.shuffle()

            // Create the quiz question with the attribute as the question text
            val quizQuestion = QuizQuestion(
                question = "What is the ${shuffledAttributes.first().first} of ${ship.name}?",
                options = options.filter { it.isNotBlank() }, // Filter out blank options
                correctAnswer = shuffledAttributes?.first()?.second ?: ""
            )
            quizQuestions.add(quizQuestion)
        }
        return quizQuestions
    }
}