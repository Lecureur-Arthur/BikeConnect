package com.example.bikeconnect

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream

class Viewerpage : AppCompatActivity() {
    private lateinit var speedTextView: TextView
    private lateinit var distanceCoveredTextView: TextView
    private lateinit var distanceLeftTextView: TextView
    private lateinit var rideTimeTextView: TextView
    private lateinit var heartRateTextView: TextView
    private lateinit var altitudeTextView: TextView
    private lateinit var mapImageView: ImageView
    private  lateinit var Buttonretour:Button

    private val handler = Handler(Looper.getMainLooper())
    private val updateInterval = 2000L
    private var currentIndex = 0
    private var rideDataArray: JSONArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cycle)

        // Initialize TextViews
        speedTextView = findViewById(R.id.speedTextView)
        distanceCoveredTextView = findViewById(R.id.distanceCoveredTextView)
        distanceLeftTextView = findViewById(R.id.distanceLeftTextView)
        rideTimeTextView = findViewById(R.id.rideTimeTextView)
        heartRateTextView = findViewById(R.id.heartRateTextView)
        altitudeTextView = findViewById(R.id.altitudeTextView)
        Buttonretour=findViewById(R.id.retourButton)
        Buttonretour.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        // Initialize ImageView
        mapImageView = findViewById(R.id.mapview)

        // Load JSON data
        rideDataArray = loadJsonArrayFromAsset("data.json")

        // Check if JSON loaded successfully
        if (rideDataArray == null) {
            Toast.makeText(this, "Erreur lors du chargement des données JSON", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "JSON chargé avec ${rideDataArray!!.length()} entrées", Toast.LENGTH_SHORT).show()
        }

        // Start updating data
        startUpdatingData()
    }

    private fun startUpdatingData() {
        handler.post(object : Runnable {
            override fun run() {
                updateDataFromJson()
                handler.postDelayed(this, updateInterval)
            }
        })
    }

    private fun updateDataFromJson() {
        try {
            rideDataArray?.let { dataArray ->
                if (currentIndex < dataArray.length()) {
                    val rideData = dataArray.getJSONObject(currentIndex)

                    // Parse values
                    val speed = rideData.getDouble("speed")
                    val distanceCovered = rideData.getDouble("distance_covered")
                    val distanceLeft = rideData.getDouble("distance_left")
                    val rideTime = rideData.getString("ride_time")
                    val heartRate = rideData.getInt("heart_rate")
                    val altitude = rideData.getInt("altitude")

                    // Update TextViews
                    speedTextView.text = "Speed\n${speed} Km/h"
                    distanceCoveredTextView.text = "Distance covered\n${distanceCovered} Km"
                    distanceLeftTextView.text = "Distance left\n${distanceLeft} Km"
                    rideTimeTextView.text = "Ride time\n${rideTime}"
                    heartRateTextView.text = "Heart Rate\n${heartRate} BPM"
                    altitudeTextView.text = "Altitude\n${altitude} m"

                    // Debug logs
                    println("Mise à jour avec les données de l'indice $currentIndex")

                    // Move to next data
                    currentIndex++
                } else {
                    // Reset index if at the end of the data array
                    currentIndex = 0
                    println("Reprise au début des données")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Erreur lors de l'actualisation des données", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadJsonArrayFromAsset(filename: String): JSONArray? {
        return try {
            val inputStream: InputStream = assets.open(filename)
            val json = inputStream.bufferedReader().use { it.readText() }
            val jsonObject = JSONObject(json)
            val dataArray = jsonObject.getJSONArray("data")

            // Log for debugging
            println("Fichier JSON chargé : ${dataArray.length()} entrées trouvées.")
            dataArray
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
