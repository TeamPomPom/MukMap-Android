package com.example.presentation.ui.screens.splash

import com.example.presentation.base.viewmodel.ViewEvent
import com.example.presentation.base.viewmodel.ViewSideEffect
import com.example.presentation.base.viewmodel.ViewState
import team.pompom.mukmap.model.restaurants.RestaurantsEntity

class SplashContract {

    sealed class Event : ViewEvent {
        object SuccessToGetRestaurant : Event()
    }
    data class State(
        val restaurants: List<RestaurantsEntity.Restaurant>,
        val networkLoading: Boolean,
        val networkError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation() : Effect() {
            object MoveToMain : Navigation()
            object MoveToPlayStore : Navigation()
        }
    }
}