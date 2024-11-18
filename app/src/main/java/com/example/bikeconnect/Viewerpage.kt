package com.example.bikeconnect

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream

class Viewerpage : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var speedTextView: TextView
    private lateinit var distanceCoveredTextView: TextView
    private lateinit var distanceLeftTextView: TextView
    private lateinit var rideTimeTextView: TextView
    private lateinit var heartRateTextView: TextView
    private lateinit var altitudeTextView: TextView
    private lateinit var googleMap: GoogleMap

    private val handler = Handler(Looper.getMainLooper())
    private val updateInterval = 2000L // Update every 2 seconds
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

        // Initialize Map
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Load JSON data from assets
        rideDataArray = loadJsonArrayFromAsset("ride_data.json")

        // Start updating data in real-time
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
            // Check if there is valid data to display
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
                    val latitude = rideData.getDouble("latitude")
                    val longitude = rideData.getDouble("longitude")

                    // Update TextViews
                    speedTextView.text = "Speed\n${speed} Km/h"
                    distanceCoveredTextView.text = "Distance covered\n${distanceCovered} Km"
                    distanceLeftTextView.text = "Distance left\n${distanceLeft} Km"
                    rideTimeTextView.text = "Ride time\n${rideTime}"
                    heartRateTextView.text = "Heart Rate\n${heartRate} BPM"
                    altitudeTextView.text = "Altitude\n${altitude} m"

                    // Update Map
                    if (::googleMap.isInitialized) {
                        val location = LatLng(latitude, longitude)
                        googleMap.clear()
                        googleMap.addMarker(MarkerOptions().position(location).title("Current Location"))
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
                    }

                    // Move to the next data entry
                    currentIndex++
                } else {
                    // Reset to the first entry if we reach the end
                    currentIndex = 0
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error loading data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadJsonArrayFromAsset(filename: String): JSONArray? {
        return try {
            val inputStream: InputStream = assets.open(filename)
            val json = inputStream.bufferedReader().use { it.readText() }
            val jsonObject = JSONObject(json)
            jsonObject.getJSONArray("ride_data")
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
    }
}
