package com.example.quiziapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quiziapp.adapters.OptionAdapter
import com.example.quiziapp.adapters.QuizAdapter
import com.example.quiziapp.models.Question
import com.example.quiziapp.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {

    var quizzes: MutableList<Quiz>? = null
    var question: MutableMap<String, Question>? = null
    var index = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        setUpFirestore()
        setUpEvent()
    }

    private fun setUpEvent() {
        btnPrevious.setOnClickListener {
            index--
            bindViews()
        }

        btnNext.setOnClickListener {
            index++
            bindViews()
        }

        btnSubmit.setOnClickListener {
            Log.d("ANSWER",question.toString())
        }
    }

    private fun setUpFirestore() {
        val firestore = FirebaseFirestore.getInstance()
        val date = intent.getStringExtra("DATE")
        if (date != null) {
            firestore.collection("quizzes").whereEqualTo("title", date)
                .get()
                .addOnSuccessListener {
                    if (it != null && !it.isEmpty) {
                        quizzes = it.toObjects(Quiz::class.java)
                        question = quizzes!![0].questions
                        bindViews()
                    }
                }
        }
    }

    private fun bindViews() {

        btnNext.visibility = View.GONE
        btnPrevious.visibility = View.GONE
        btnSubmit.visibility = View.GONE

        if (index == 1) {
            btnNext.visibility = View.VISIBLE
        } else if (index == question!!.size) {
            btnSubmit.visibility = View.VISIBLE
            btnPrevious.visibility = View.VISIBLE
        } else {
            btnNext.visibility = View.VISIBLE
            btnPrevious.visibility = View.VISIBLE
        }


        val question = question!!["question$index"]
        question?.let {

            description.text = it.description
            val optionAdapter = OptionAdapter(this, it)
            optionList.layoutManager = LinearLayoutManager(this)
            optionList.adapter = optionAdapter
            optionList.setHasFixedSize(true)

        }
    }
}