package com.example.presentation.main.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.presentation.main.MainContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import team.pompom.mukmap.model.restaurants.RestaurantsEntity

@Composable
fun MainScreen(
    state: MainContract.State,
    effectFlow: Flow<MainContract.Effect>,
    onEventSent: (event: MainContract.Event) -> Unit,
    onNavigationFlow: (MainContract.Effect.Navigation) -> Unit
) {
    LaunchedEffect(key1 = true) {
        effectFlow.onEach { effect ->
            when (effect) {
                is MainContract.Effect.Navigation -> {
                    onNavigationFlow(effect)
                }
                else -> {}
            }
        }.collect()
    }

    when {
        state.restaurant.isNotEmpty() -> MapScreen(state.restaurant)
    }
}

@Composable
fun MapScreen(
    list: List<RestaurantsEntity.Restaurant>
) {
    Text(text = list.toString())
}