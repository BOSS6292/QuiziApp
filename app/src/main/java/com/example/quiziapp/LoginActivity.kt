package com.example.quiziapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()

        loginButton.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val email: String = loginEmailAddress.text.toString()
        val password: String = loginPassword.text.toString()

        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Blank Email or Password", Toast.LENGTH_SHORT).show()
            return
        }

        if (email.matches(emailPattern.toRegex())) {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error Occured !!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}