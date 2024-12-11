package com.dimalahmad.foodheroes.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dimalahmad.foodheroes.MainActivity
import com.dimalahmad.foodheroes.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById<Button>(R.id.btnLogin)

        loginButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.etEmail).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()

            // Simulate login check
            if (email.isNotEmpty() && password.isNotEmpty()) {
                val sharedPref = getSharedPreferences("APP_PREF", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("USER_EMAIL", email)
                editor.putString("USER_ROLE", "partner") // Set role dynamically
                editor.apply()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
}