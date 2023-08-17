package com.example.presentation.ui.screens.search

import com.example.presentation.base.viewmodel.ViewEvent
import com.example.presentation.base.viewmodel.ViewSideEffect
import com.example.presentation.base.viewmodel.ViewState
import team.pompom.mukmap.model.restaurants.RestaurantsEntity

interface SearchContract {
    sealed class Event : ViewEvent {
        object ClickBackButton : Event()
        class ClickRestaurant(val restaurant: RestaurantsEntity.Restaurant) : Event()
        class EnterSearchText(val searchText: String) : Event()
    }

    data class State(
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