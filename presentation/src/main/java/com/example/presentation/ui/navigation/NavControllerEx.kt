package com.example.presentation.ui.navigation

import androidx.activity.ComponentActivity
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

fun NavController.popOrFinish(activity: ComponentActivity) {
    if (popBackStack().not()) {
        activity.finish()
    }
}