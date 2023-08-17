package com.example.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.presentation.ui.screens.splash.SplashContract
import com.example.presentation.ui.screens.splash.SplashViewModel
import com.example.presentation.ui.screens.splash.screen.SplashScreen

@Composable
fun SplashScreenDestination(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    SplashScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                SplashContract.Effect.Navigation.MoveToMain -> {
                    navController.navigateToMain()
                }
                SplashContract.Effect.Navigation.MoveToPlayStore -> TODO()
            }
        }
    )
}