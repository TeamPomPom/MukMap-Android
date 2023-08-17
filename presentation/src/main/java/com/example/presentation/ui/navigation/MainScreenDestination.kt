package com.example.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.presentation.ui.screens.main.MainContract
import com.example.presentation.ui.screens.main.MainViewModel
import com.example.presentation.ui.screens.main.screen.MainScreen
import team.pompom.mukmap.model.restaurants.RestaurantsEntity

@Composable
fun MainScreenDestination(
    searchedRestaurant: RestaurantsEntity.Restaurant?,
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    searchedRestaurant?.let { viewModel.setSearchedRestaurant(searchedRestaurant) }
    MainScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationFlow = { navigation ->
            when (navigation) {
                MainContract.Effect.Navigation.MoveToSearchScreen -> navController.navigateToSearch()
            }
        }
    )
}