package com.example.presentation.navigation

import androidx.navigation.NavController

fun NavController.navigateToMain() {
    navigate(route = Navigation.Routes.MAIN) {
        popUpTo(Navigation.Routes.SPLASH) {
            inclusive = true
        }
    }
}

fun NavController.navigateToSearch() {
    navigate(route = Navigation.Routes.SEARCH)
}