package com.example.presentation.ui.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import team.pompom.mukmap.model.restaurants.RestaurantsEntity

@Composable
fun AppNavigation(
    activity: ComponentActivity
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigation.Routes.SPLASH
    ) {
        composable(
            route = Navigation.Routes.SPLASH
        ) {
            SplashScreenDestination(navController) { navController.popOrFinish(activity) }
        }

        composable(
            route = Navigation.Routes.MAIN,
        ) { navBackStackEntry ->
            val restaurant = navBackStackEntry
                .savedStateHandle
                .getStateFlow<RestaurantsEntity.Restaurant?>(Navigation.RESTAURANT, null)
                .collectAsState()

            MainScreenDestination(
                searchedRestaurant = restaurant.value,
                navController = navController
            )
        }

        composable(
            route = Navigation.Routes.SEARCH,
        ) {
            SearchScreenDestination(navController = navController) { restaurant ->
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set(Navigation.RESTAURANT, restaurant)
                navController.popOrFinish(activity)
            }
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