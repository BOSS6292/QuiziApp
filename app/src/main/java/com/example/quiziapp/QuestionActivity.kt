package com.example.quiziapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quiziapp.adapters.OptionAdapter
import com.example.quiziapp.adapters.QuizAdapter
import com.example.quiziapp.models.Question
import com.example.quiziapp.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        bindViews()
        setUpFirestore()
    }

    private fun setUpFirestore() {
        val firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quizzes").whereEqualTo("DATE","02-10-2023")
            .get()
            .addOnSuccessListener {
                Log.d("DATA",it.toObjects(Quiz::class.java).toString())
            }
    }

    private fun bindViews() {
        val question = Question(
            "What is speed unit?",
            "KMPH",
            "PHKM",
            "OPIU",
            "KJNM",
            "KMPH"
        )

        description.text = question.description
        val optionAdapter = OptionAdapter(this, question)
        optionList.layoutManager = LinearLayoutManager(this)
        optionList.adapter = optionAdapter
        optionList.setHasFixedSize(true)
    }
}