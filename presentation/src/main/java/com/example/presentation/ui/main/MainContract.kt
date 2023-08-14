package com.example.presentation.ui.main

import com.example.presentation.base.vm.ViewEvent
import com.example.presentation.base.vm.ViewSideEffect
import com.example.presentation.base.vm.ViewState
import team.pompom.mukmap.model.restaurants.RestaurantsEntity

object MainContract {
    sealed class Event : ViewEvent {
        object ClickSearch : Event()
        class ClickRestaurant(val restaurant: RestaurantsEntity.Restaurant) : Event()
        object RefreshSearchedRestaurant : Event()
    }

    data class State(
        val entireRestaurant: List<RestaurantsEntity.Restaurant>,
        val searchText: String,
        val searchedRestaurant: RestaurantsEntity.Restaurant?
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        object InitBottomSheetState : Effect()
        sealed class Navigation : Effect() {
            object MoveToSearchScreen : Navigation()
        }
    }
}