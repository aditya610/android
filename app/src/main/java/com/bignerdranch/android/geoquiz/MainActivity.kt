package com.bignerdranch.android.geoquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Gravity
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var prevButton: Button
    private lateinit var cheatButton: Button
    private lateinit var questionTextView: TextView

    private val Tag = "MainActivity"
    private val KEY_INDEX = "index"
    //private var answerList = mutableListOf<Int>(questionBank.size)
    //private var scoreList = mutableListOf<Int>(questionBank.size)

    private val quizViewModel:QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(Tag,"onCreate")
        setContentView(R.layout.activity_main)
        val  currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        cheatButton = findViewById(R.id.cheat_button)
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
            //currentIndex =(currentIndex + 1) % questionBank.size
            quizViewModel.moveToNext()
            updateQuestion()
        }

        nextButton.setOnClickListener {
            //currentIndex =(currentIndex + 1) % questionBank.size
            quizViewModel.moveToNext()
            updateQuestion()

        }

        cheatButton.setOnClickListener {
            val intent = Intent(this, CheatActivity::class.java)
            startActivity(intent)
        }

        prevButton.setOnClickListener {
//            currentIndex = (currentIndex -1)  % questionBank.size
//            if(currentIndex < 0) {
//                currentIndex = questionBank.size - 1
//            }
            quizViewModel.moveToNext()
            updateQuestion()
        }

        updateQuestion()
    }
    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer:Boolean)
    {
        //answerList.add(currentIndex, 1)
        //if (answerList.elementAtOrNull(currentIndex) == null) {
            //val correctAnswer = questionBank[currentIndex].answer;
            val correctAnswer = quizViewModel.currentQuestionAnnswer
            val message = if (userAnswer == correctAnswer) {
                //scoreList.add(currentIndex,1)
                R.string.correct_toast
            } else {
                //scoreList.add(currentIndex, 0)
                R.string.incorrect_toast
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
       // }
//        else{
//            Toast.makeText(this,R.string.questionAnswered,Toast.LENGTH_SHORT).show()
//            if (answerList.sum() == questionBank.size)
//            {
//                val sum = scoreList.sum()*20
//                Toast.makeText(this,"your percentage is "+sum,Toast.LENGTH_SHORT).show()
//            }
//            else{
//                Toast.makeText(this,R.string.questionAnswered,Toast.LENGTH_SHORT).show()
//            }
//        }

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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(Tag, "onSavedInstance")
        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
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