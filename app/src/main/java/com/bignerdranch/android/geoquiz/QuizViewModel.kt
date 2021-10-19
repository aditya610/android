package com.bignerdranch.android.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {
    private val questionBank = listOf(
        Question(R.string.question_oceans,true),
        Question(R.string.question_oceans1,false),
        Question(R.string.question_oceans2,true),
        Question(R.string.question_oceans3,false)
    )
    var currentIndex = 0
    val currentQuestionAnnswer: Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId
    fun moveToNext(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }
}