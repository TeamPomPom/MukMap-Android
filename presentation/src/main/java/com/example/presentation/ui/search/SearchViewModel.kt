package com.example.presentation.ui.search

import com.example.presentation.base.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import team.pompom.mukmap.usecase.RestaurantUseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val restaurantUseCase: RestaurantUseCase
) :
    BaseViewModel<SearchContract.Event, SearchContract.State, SearchContract.Effect>() {
    override fun setInitialState(): SearchContract.State = SearchContract.State(
        searchText = "",
        searchResult = listOf(),
        hasError = false
    )

    override fun handleEvents(event: SearchContract.Event) {
        when (event) {
            SearchContract.Event.ClickBackButton -> setEffect { SearchContract.Effect.Navigation.NavigateToBack }
            is SearchContract.Event.ClickRestaurant -> setEffect {
                SearchContract.Effect.Navigation.ShowRestaurantDetail(
                    event.restaurant
                )
            }
            is SearchContract.Event.EnterSearchText -> searchRestaurant(keyword = event.searchText)
        }
    }

    fun searchRestaurant(keyword: String) {
        restaurantUseCase
            .searchRestaurants(keyword = keyword)
            .viewModelsIn(
                onSuccess = {
                    setState {
                        copy(
                            searchResult = it.restaurants,
                            searchText = keyword,
                            hasError = false
                        )
                    }
                },
                onError = {
                    setState { copy(hasError = true) }
                }
            )
    }
}