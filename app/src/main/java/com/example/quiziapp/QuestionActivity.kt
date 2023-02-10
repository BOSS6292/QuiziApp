package com.example.quiziapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quiziapp.adapters.OptionAdapter
import com.example.quiziapp.adapters.QuizAdapter
import com.example.quiziapp.models.Question
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        bindViews()
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