package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.presentation.splash.SplashContract
import com.example.presentation.splash.SplashViewModel
import com.example.presentation.splash.screen.SplashScreen

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