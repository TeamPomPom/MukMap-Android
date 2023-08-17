package com.example.presentation.ui.screens.main

import com.example.presentation.base.viewmodel.ViewEvent
import com.example.presentation.base.viewmodel.ViewSideEffect
import com.example.presentation.base.viewmodel.ViewState
import team.pompom.mukmap.model.restaurants.RestaurantsEntity

object MainContract {
    sealed class Event : ViewEvent {
        object ClickSearch : Event()
        class ClickRestaurant(val restaurant: RestaurantsEntity.Restaurant) : Event()
        object RefreshSearchedRestaurant : Event()

        data class NaverButtonClicked(val placeId: String) : Event()

        data class YoutubeButtonClicked(val youtubeVideoId: String) : Event()
    }

    data class State(
        val entireRestaurant: List<RestaurantsEntity.Restaurant>,
        val searchText: String,
        val searchedRestaurant: RestaurantsEntity.Restaurant?
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        object InitBottomSheetState : Effect()
        data class MoveToNaverMap(val placeId: String) : Effect()

        data class MoveToYoutubeApp(val youtubeVideoId: String) : Effect()
        sealed class Navigation : Effect() {
            object MoveToSearchScreen : Navigation()
        }
    }
}