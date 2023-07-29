package com.example.presentation.main

import com.example.presentation.base.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import team.pompom.mukmap.usecase.RestaurantUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    restaurantUseCase: RestaurantUseCase
): BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {
    override fun setInitialState(): MainContract.State = MainContract.State(
        restaurant = listOf()
    )

    override fun handleEvents(event: MainContract.Event) {
        when (event) {
            else -> {}
        }
    }

    init {
        setState { copy(restaurant = restaurantUseCase.cachedRestaurant.value) }
    }
}