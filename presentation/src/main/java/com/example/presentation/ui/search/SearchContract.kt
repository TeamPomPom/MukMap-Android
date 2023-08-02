package com.example.presentation.ui.search

import com.example.presentation.base.vm.ViewEvent
import com.example.presentation.base.vm.ViewSideEffect
import com.example.presentation.base.vm.ViewState
import team.pompom.mukmap.model.restaurants.RestaurantsEntity

interface SearchContract {
    sealed class Event : ViewEvent {
        object ClickBackButton : Event()
        class ClickRestaurant(val restaurant: RestaurantsEntity.Restaurant) : Event()
        class EnterSearchText(val searchText: String) : Event()
    }

    data class State(
        val searchText: String,
        val searchResult: List<RestaurantsEntity.Restaurant>,
        val hasError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            class ShowRestaurantDetail(val clickedRestaurant: RestaurantsEntity.Restaurant) : Navigation()
            object NavigateToBack : Navigation()
        }
    }
}