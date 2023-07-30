package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.presentation.ui.main.MainViewModel
import com.example.presentation.ui.main.screen.MainScreen
import team.pompom.mukmap.model.restaurants.RestaurantsEntity

@Composable
fun MainScreenDestination(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    MainScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.handleEvents(event) },
        onNavigationFlow = { navigation ->
            when (navigation) {
                else -> {}
            }
        }
    )
}