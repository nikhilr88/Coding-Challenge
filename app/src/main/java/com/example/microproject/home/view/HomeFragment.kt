package com.example.microproject.home.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.microproject.common.view.fragment.MvvmFragment
import com.example.microproject.home.viewmodel.HomeViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeFragment : MvvmFragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var carSpeedJob: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // If we use any API to get car live Speed than this function use
        carSpeedJob = lifecycleScope.launch {
            homeViewModel.carSpeedLiveData.collect { carSpeed ->
                // Handle the new car speed value here
                homeViewModel.monitorSpeed(carSpeed)
            }
        }


        if (homeViewModel.showAlert) {
            // Show alert dialog
        }
    }



}