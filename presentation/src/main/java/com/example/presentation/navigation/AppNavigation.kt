package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigation.Routes.SPLASH
    ) {
        composable(
            route = Navigation.Routes.SPLASH
        ) {
            SplashScreenDestination(navController)
        }

        composable(
            route = Navigation.Routes.MAIN,
        ) { navBackStackEntry ->
            MainScreenDestination(navController = navController)
        }

        composable(
            route = Navigation.Routes.SEARCH,
        ) {
            SearchScreenDestination(navController = navController)
        }
    }
}

object Navigation {
    const val RESTAURANT = "restaurant"
    object Routes {
        const val SPLASH = "splash"
        const val MAIN = "main"
        const val SEARCH = "search"
    }
}