package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.presentation.ui.search.SearchContract
import com.example.presentation.ui.search.SearchViewModel
import com.example.presentation.ui.search.screen.SearchScreen

@Composable
fun SearchScreenDestination(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    SearchScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.handleEvents(event) },
        onNavigationEffect = { effect ->
            when (effect) {
                SearchContract.Effect.Navigation.NavigateToBack -> navController.popBackStack()
                is SearchContract.Effect.Navigation.ShowRestaurantDetail -> navController.navigateToMain()
            }
        }
    )
}