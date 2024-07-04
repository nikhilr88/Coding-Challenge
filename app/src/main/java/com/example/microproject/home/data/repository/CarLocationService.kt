package com.example.microproject.home.data.repository

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import com.example.microproject.home.viewmodel.HomeViewModel


class SpeedMonitoringService : Service() {

    private lateinit var locationManager: LocationManager
    private var maxSpeed: Int = 0

    override fun onCreate() {
        super.onCreate()
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        // Initialize maxSpeed from repository or any other source
        maxSpeed = 60 // Default max speed for demonstration
    }

    @SuppressLint("MissingPermission")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
        return START_STICKY
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            val currentSpeed = location.speed * 3.6 // Convert m/s to km/h
            viewModel.monitorSpeed(currentSpeed.toInt())
        }

        @Deprecated("Deprecated in Java")
        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        locationManager.removeUpdates(locationListener)
    }

    companion object {
        lateinit var viewModel: HomeViewModel
    }
}