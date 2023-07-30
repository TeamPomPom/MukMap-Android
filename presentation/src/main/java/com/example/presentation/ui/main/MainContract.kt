package com.example.presentation.ui.main

import com.example.presentation.base.vm.ViewEvent
import com.example.presentation.base.vm.ViewSideEffect
import com.example.presentation.base.vm.ViewState
import team.pompom.mukmap.model.restaurants.RestaurantsEntity

object MainContract {
    sealed class Event : ViewEvent
    data class State(
        val restaurant: List<RestaurantsEntity.Restaurant>
    ) : ViewState
    sealed class  Effect : ViewSideEffect {
        sealed class Navigation : Effect()
    }
}