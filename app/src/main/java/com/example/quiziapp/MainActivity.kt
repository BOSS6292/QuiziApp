package com.example.quiziapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quiziapp.adapters.QuizAdapter
import com.example.quiziapp.models.Quiz
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var adapter: QuizAdapter
    lateinit var firestore: FirebaseFirestore
    var quizList = mutableListOf<Quiz>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpFirestore()
        setDummyData()
        setUpView()
    }

    private fun setUpFirestore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if (value == null || error != null) {
                Toast.makeText(this, "Something Happened", Toast.LENGTH_SHORT).show()
            }
            if (value != null) {
                Log.d("DATA", value.toObjects(Quiz::class.java).toString())
            }
            quizList.clear()
            quizList.addAll(value!!.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
        }
    }

    private fun setDummyData() {
        quizList.add(Quiz("1", "Tejas"))
        quizList.add(Quiz("1", ""))
        quizList.add(Quiz("1", "Tejas"))
        quizList.add(Quiz("1", "Tejas"))
        quizList.add(Quiz("1", "Tejas"))
        quizList.add(Quiz("1", "Tejas"))
        quizList.add(Quiz("1", "Tejas"))
        quizList.add(Quiz("1", "Tejas"))
        quizList.add(Quiz("1", "Tejas"))
        quizList.add(Quiz("1", "Tejas"))
        quizList.add(Quiz("1", "Tejas"))
        quizList.add(Quiz("1", "Tejas"))
    }

    private fun setUpView() {
        setUpDrawerLayout()
        setUpRecycleView()
        setUpDatePicker()
    }

    private fun setUpDatePicker() {
        btnDatePicker.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "DatePicker")
            datePicker.addOnPositiveButtonClickListener {
                Log.d("DATE PICKER", datePicker.headerText)
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra("DATE", datePicker.headerText)
                startActivity(intent)
            }
            datePicker.addOnNegativeButtonClickListener {
                Log.d("DATE PICKER", datePicker.headerText)
            }
            datePicker.addOnCancelListener {
                Log.d("DATE PICKER", "Date Picker Cancelled")
            }
        }
    }

    private fun setUpRecycleView() {
        adapter = QuizAdapter(this, quizList)
        quizRecyclerView.layoutManager = GridLayoutManager(this, 2)
        quizRecyclerView.adapter = adapter
    }

    private fun setUpDrawerLayout() {
        setSupportActionBar(topAppBar)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, mainDrawer, R.string.app_name, R.string.app_name)
        actionBarDrawerToggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}