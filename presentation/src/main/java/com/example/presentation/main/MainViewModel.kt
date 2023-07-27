package com.example.presentation.main

import android.util.Log
import com.example.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import team.pompom.mukmap.usecase.InitUseCase
import team.pompom.mukmap.usecase.RestaurantUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val initUseCase: InitUseCase,
    private val restaurantUseCase: RestaurantUseCase
) : BaseViewModel() {

    fun init() {
        initUseCase
            .getAppInitData("BaekMap")
            .viewModelsIn(
                onSuccess = {
                    Log.d("Ram test", "success, init data = $it")
                },
                onError = {
                    it.printStackTrace()
                    Log.d("Ram test", "error 1, error data = $it")
                }
            )

        restaurantUseCase
            .getRestaurants("BaekMap")
            .viewModelsIn(
                onSuccess = {
                    Log.d("Ram test", "success, restaurants data = $it")
                },
                onError = {
                    it.printStackTrace()
                    Log.d("Ram test", "error 2, error data = $it")
                }
            )
    }
}