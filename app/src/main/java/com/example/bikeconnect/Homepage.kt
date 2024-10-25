package com.example.bikeconnect

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Homepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page) // Remplacez par le nom de votre fichier XML

        val userIcon = findViewById<ImageView>(R.id.image) // Assurez-vous que l'ID est correct
        userIcon.setOnClickListener { // Créez une nouvelle intention pour naviguer vers LoginActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
