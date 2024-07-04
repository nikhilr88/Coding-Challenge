package com.example.microproject.home.data.repository

import com.example.microproject.home.data.util.CarInfo

class CarRepository() : ICarRepository{

    override suspend fun getCarInfo(customerId : String): CarInfo {
        // Call API to get data from Firebase Database or AWS
        return CarInfo("123456789", 100)
    }

    override suspend fun getCarCurrentSpeed(): Int {
        //Car Speed we can get from Car Manager/ LocationManager
        return 110
    }

    override suspend fun sendCarExceededSpeed(customerId : String , speed: Int) {
        TODO("Send this speed Firebase Database or AWS to use for car rental company ")
    }
}