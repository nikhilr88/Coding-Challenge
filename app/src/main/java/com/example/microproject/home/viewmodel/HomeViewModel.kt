package com.example.microproject.home.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.microproject.common.viewmodel.BaseViewModel
import com.example.microproject.home.data.repository.ICarRepository
import com.example.microproject.home.data.util.CarInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeViewModel(
    private val carRepository: ICarRepository
) : BaseViewModel() {
    private val _carInfo = MutableSharedFlow<CarInfo>()
    private val carInfo: Flow<CarInfo> = _carInfo.asSharedFlow()

    private val _carSpeedLiveData = MutableSharedFlow<Int>()
    val carSpeedLiveData: SharedFlow<Int> = _carSpeedLiveData.asSharedFlow()


    var showAlert: Boolean = false

    init {
      getCarCurrentSpeed()
    }

    fun getCarData() {
        viewModelScope.launch {
            _carInfo.emit(carRepository.getCarInfo("123456789"))
        }
    }

    // carSpeed we can get from Car Manager/ LocationManager
     fun monitorSpeed(carSpeed: Int) {
         viewModelScope.launch {
             carInfo.let {
                 if (carSpeed > it.first().maxSpeed) {
                     // Notify rental company
                     sendNotification("Speed Limit Exceeded!",carSpeed)
                     carRepository.sendCarExceededSpeed("123456789",carSpeed)
                     showAlert = true
                 }
             }
         }
     }

    // If we use any API to get car live Speed than this function use
    fun getCarCurrentSpeed() {
        viewModelScope.launch {
            _carSpeedLiveData.emit(carRepository.getCarCurrentSpeed())
        }
    }

    private fun sendNotification(message: String, carSpeed: Int) {
        // send notification using Firebase Cloud Messaging
    }
}