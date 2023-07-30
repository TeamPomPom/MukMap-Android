package com.example.presentation.ui.splash.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.theme.MukMapTheme
import com.example.presentation.ui.base.ImageWithTextSlot
import com.example.presentation.ui.splash.SplashContract
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
        state.networkLoading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                SplashLoading()
            }
        }
        state.networkError -> SplashNetworkError()
        state.restaurants.isNotEmpty() -> onEventSent(SplashContract.Event.SuccessToGetRestaurant)
    }
}


@Composable
fun SplashNetworkError() {
    // TODO : ErrorPage View 작업
    Text(text = "에러 있어용")
}