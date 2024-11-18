package com.example.bikeconnect

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val dbHelper = DBHelper(this)

        val buttonCreate = findViewById<Button>(R.id.buttonCreateAccount)
        buttonCreate.setOnClickListener {
            val firstName = findViewById<EditText>(R.id.editTextFirstName).text.toString()
            val lastName = findViewById<EditText>(R.id.editTextLastName).text.toString()
            val email = findViewById<EditText>(R.id.editTextEmail).text.toString()
            val password = findViewById<EditText>(R.id.editTextPassword).text.toString()
            val confirmPassword = findViewById<EditText>(R.id.editTextConfirmPassword).text.toString()

            if (password == confirmPassword) {
                val db = dbHelper.writableDatabase
                val values = ContentValues().apply {
                    put("first_name", firstName)
                    put("last_name", lastName)
                    put("email", email)
                    put("password", password)
                }
                db.insert("users", null, values)
                Toast.makeText(this, "User registered", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
