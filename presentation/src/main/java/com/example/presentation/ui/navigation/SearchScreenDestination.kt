package com.example.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.presentation.ui.screens.search.SearchContract
import com.example.presentation.ui.screens.search.SearchViewModel
import com.example.presentation.ui.screens.search.screen.SearchScreen
import team.pompom.mukmap.model.restaurants.RestaurantsEntity

@Composable
fun SearchScreenDestination(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel(),
    popStackAction: (restaurant: RestaurantsEntity.Restaurant) -> Unit
) {
    SearchScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationEffect = { effect ->
            when (effect) {
                SearchContract.Effect.Navigation.NavigateToBack -> navController.popBackStack()
                is SearchContract.Effect.Navigation.ShowRestaurantDetail -> popStackAction.invoke(effect.clickedRestaurant)
            }
        }
    )
}