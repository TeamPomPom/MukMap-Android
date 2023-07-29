package com.example.presentation.splash.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.presentation.main.MainContract
import com.example.presentation.splash.SplashContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun SplashScreen(
    state: SplashContract.State,
    effectFlow: Flow<SplashContract.Effect>,
    onEventSent: (event: SplashContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: SplashContract.Effect.Navigation) -> Unit
) {
    LaunchedEffect(key1 = true) {
        effectFlow.onEach { effect ->
            when (effect) {
                is SplashContract.Effect.Navigation -> {
                    onNavigationRequested(effect)
                }
            }
        }.collect()
    }

    when {
        state.networkLoading -> SplashLoading()
        state.networkError -> SplashNetworkError()
        state.restaurants.isNotEmpty() -> onEventSent(SplashContract.Event.SuccessToGetRestaurant)
    }
}

@Composable
fun SplashLoading() {
    Text(text = "스플래쉬 로딩화면 ...")
}

@Composable
fun SplashNetworkError() {
    Text(text = "에러 있어용")
}