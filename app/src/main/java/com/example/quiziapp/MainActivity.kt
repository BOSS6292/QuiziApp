package com.example.quiziapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quiziapp.adapters.QuizAdapter
import com.example.quiziapp.models.Quiz
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var adapter: QuizAdapter
    var quizList = mutableListOf<Quiz>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setDummyData()
        setUpView()
    }

    private fun setDummyData() {
        quizList.add(Quiz("1","Tejas"))
        quizList.add(Quiz("1","Tejas"))
        quizList.add(Quiz("1","Tejas"))
        quizList.add(Quiz("1","Tejas"))
        quizList.add(Quiz("1","Tejas"))
        quizList.add(Quiz("1","Tejas"))
        quizList.add(Quiz("1","Tejas"))
        quizList.add(Quiz("1","Tejas"))
        quizList.add(Quiz("1","Tejas"))
        quizList.add(Quiz("1","Tejas"))
        quizList.add(Quiz("1","Tejas"))
        quizList.add(Quiz("1","Tejas"))
    }

    private fun setUpView() {
        setUpDrawerLayout()
        setUpRecycleView()
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