package com.example.microproject.home.data.repository

import com.example.microproject.home.data.util.CarInfo

interface ICarRepository {
    suspend fun getCarInfo(customerId :String): CarInfo
    suspend fun getCarCurrentSpeed(): Int
    suspend fun sendCarExceededSpeed(customerId : String, speed: Int)
}