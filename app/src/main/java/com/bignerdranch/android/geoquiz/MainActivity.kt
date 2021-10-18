package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast



class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var prevButton: Button
    private lateinit var questionTextView: TextView
    private val questionBank = listOf(
            Question(R.string.question_oceans,true),
            Question(R.string.question_oceans1,false),
            Question(R.string.question_oceans2,true),
            Question(R.string.question_oceans3,false)
    )
    private var currentIndex = 0
    private val Tag = "MainActivity"
    private var answerList = mutableListOf<Int>(questionBank.size)
    private var scoreList = mutableListOf<Int>(questionBank.size)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(Tag,"onCreate")
        setContentView(R.layout.activity_main)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)
        questionTextView = findViewById(R.id.question_text_view)
            trueButton.setOnClickListener {
                checkAnswer(true)
            }
            falseButton.setOnClickListener {
                checkAnswer(false)
            }

        questionTextView.setOnClickListener {
            currentIndex =(currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        nextButton.setOnClickListener {
            currentIndex =(currentIndex + 1) % questionBank.size
            updateQuestion()

        }

        prevButton.setOnClickListener {
            currentIndex = (currentIndex -1)  % questionBank.size
            if(currentIndex < 0) {
                currentIndex = questionBank.size - 1
            }
            updateQuestion()
        }

        updateQuestion()
    }
    private fun updateQuestion(){
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer:Boolean)
    {
        answerList.add(currentIndex, 1)
        if (answerList.elementAtOrNull(currentIndex) == null) {
            val correctAnswer = questionBank[currentIndex].answer;
            val message = if (userAnswer == correctAnswer) {
                scoreList.add(currentIndex,1)
                R.string.correct_toast
            } else {
                scoreList.add(currentIndex, 0)
                R.string.incorrect_toast
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,R.string.questionAnswered,Toast.LENGTH_SHORT).show()
            if (answerList.sum() == questionBank.size)
            {
                val sum = scoreList.sum()*20
                Toast.makeText(this,"your percentage is "+sum,Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,R.string.questionAnswered,Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(Tag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(Tag, "Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(Tag, "Pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(Tag, "Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(Tag, "Destroy")
    }
}