package com.example.bikeconnect

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class Homepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page) // Remplacez par le nom de votre fichier XML

        val userIcon = findViewById<ImageView>(R.id.image) // Assurez-vous que l'ID est correct
        userIcon.setOnClickListener { // Créez une nouvelle intention pour naviguer vers MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Initialisation du Spinner pour les tailles de roue
        val spinnerWheelSize: Spinner = findViewById(R.id.spinnerWheelSize)

        // Récupérer le tableau de chaînes à partir de strings.xml
        val adapter = ArrayAdapter.createFromResource(this,
            R.array.wheel_sizes, android.R.layout.simple_spinner_item)

        // Spécifier le layout à utiliser pour les éléments de la liste
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Appliquer l'adaptateur au Spinner
        spinnerWheelSize.adapter = adapter
    }
}
