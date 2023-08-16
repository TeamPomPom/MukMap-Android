package com.example.presentation.ui.main

import com.example.presentation.base.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import team.pompom.mukmap.model.restaurants.RestaurantsEntity
import team.pompom.mukmap.usecase.RestaurantUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    restaurantUseCase: RestaurantUseCase
): BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {
    override fun setInitialState(): MainContract.State = MainContract.State(
        entireRestaurant = listOf(),
        searchText = "음식 메뉴, 지역을 검색 해 보세요",
        searchedRestaurant = null
    )

    override fun handleEvents(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.ClickRestaurant -> {
                setState { copy(searchedRestaurant = event.restaurant.copy()) }
            }
            MainContract.Event.ClickSearch -> {
                setEffect { MainContract.Effect.Navigation.MoveToSearchScreen }
            }
            MainContract.Event.RefreshSearchedRestaurant -> {
                setState { copy(searchedRestaurant = null) }
                setEffect { MainContract.Effect.InitBottomSheetState }
            }
            is MainContract.Event.NaverButtonClicked -> {
                setEffect { MainContract.Effect.MoveToNaverMap(event.placeId) }
            }
        }
    }

    init {
        setState { copy(entireRestaurant = restaurantUseCase.cachedRestaurant.value) }
    }

    fun setSearchedRestaurant(searchedRestaurant: RestaurantsEntity.Restaurant) {
        setState { copy(searchedRestaurant = searchedRestaurant) }
    }
}