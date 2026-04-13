package com.example.imadgymappassignment

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

data class Flashcard(
    val statement: String,
    val isHack: Boolean
)

class MainActivity : AppCompatActivity() {
    private var score = 0
    private var currentIndex = 0
    private val questions = listOf(
        Flashcard("Drinking coffee fixes dehydration", false),
        Flashcard("Working out everyday builds more muscle", false),
        Flashcard("Stretching before working out reduces risk of injury", true),
        Flashcard("Drinking water during workouts improves performance", true),
        Flashcard("You need to feel sore after every workout for it to be effective", false),
        Flashcard("Protein helps repair and build muscle", true),
        Flashcard("Lifting weights will automatically make you bulky", false),
        Flashcard("Sleeping well is important for muscle growth", true),
        Flashcard("You can target fat loss in one specific area", false),
        Flashcard("Stretching after workouts can improve flexibility", true),
        Flashcard("More sweat means more fat loss", false),
        Flashcard("Rest days are important for muscle recovery", true),
        Flashcard("Doing only cardio is the best way to lose weight", false),
        Flashcard("Progressive overload helps build muscle", true),
        Flashcard("Skipping warm-ups increases injury risk", true),
        Flashcard("You must work out every single day to see results", false),
        Flashcard("Eating immediately after a workout is mandatory for gains", false),
        Flashcard("Hydration affects strength and endurance", true)
    )

    private lateinit var questionText: TextView
    private lateinit var scoreText: TextView
    private lateinit var feedbackText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize Views
        questionText = findViewById(R.id.questionText)
        scoreText = findViewById(R.id.scoreText)
        feedbackText = findViewById(R.id.feedbackText)
        val btnHack = findViewById<Button>(R.id.btnHack)
        val btnMyth = findViewById<Button>(R.id.btnMyth)

        // Set up button listeners
        btnHack.setOnClickListener {
            checkAnswer(true)
        }

        btnMyth.setOnClickListener {
            checkAnswer(false)
        }

        // Load the first question
        loadQuestion()
    }

    private fun loadQuestion() {
        if (currentIndex < questions.size) {
            questionText.text = questions[currentIndex].statement
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        if (currentIndex >= questions.size) return

        val correctAnswer = questions[currentIndex].isHack

        if (userAnswer == correctAnswer) {
            score++
            feedbackText.text = "Correct!"
        } else {
            feedbackText.text = "Wrong!"
        }

        scoreText.text = "Score: $score"
        currentIndex++

        if (currentIndex < questions.size) {
            loadQuestion()
        } else {
            feedbackText.text = "Quiz finished!"
            questionText.text = "You've completed the quiz."
        }
    }
}
