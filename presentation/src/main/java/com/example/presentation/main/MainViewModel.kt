package com.example.presentation.main

import android.util.Log
import com.example.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import team.pompom.mukmap.usecase.InitUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val initUseCase: InitUseCase
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
                    Log.d("Ram test", "error, error data = $it")
                }
            )
    }
}