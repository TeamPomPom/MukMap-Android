package com.example.presentation.ui.screens.splash.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.presentation.theme.MukMapTheme
import com.example.presentation.ui.screens.common.MukMapPreviews
import com.example.presentation.ui.screens.common.composable.errordialog.OneButtonPopUpDialog
import com.example.presentation.ui.screens.common.previewparameter.dummyRestaurant
import com.example.presentation.ui.screens.splash.SplashContract
import com.example.presentation.ui.screens.splash.screen.components.SplashLoading
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
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
                is SplashContract.Effect.Navigation -> onNavigationRequested(effect)
            }
        }.collect()
    }

    LaunchedEffect(key1 = state.restaurants) {
        if (state.restaurants.isNotEmpty()) {
            onEventSent(SplashContract.Event.SuccessToGetRestaurant)
        }
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
        state.errorStatus != null -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                OneButtonPopUpDialog(
                    contentsText = state.errorStatus.errorMsg,
                    buttonText = "확인",
                    onClickButton = {
                        onEventSent.invoke(SplashContract.Event.OnErrorInSplash)
                    },
                    onDismissRequest = {
                        onEventSent.invoke(SplashContract.Event.OnErrorInSplash)
                    })
            }
        }
    }
}

@Composable
@MukMapPreviews
fun SplashScreenPreview() {
    MukMapTheme {
        SplashScreen(
            state = SplashContract.State(
                restaurants = listOf(dummyRestaurant, dummyRestaurant, dummyRestaurant),
                networkLoading = true,
                errorStatus = null,
            ),
            effectFlow = flowOf(),
            onEventSent = {},
            onNavigationRequested = {}
        )
    }
}

@Composable
@MukMapPreviews
fun SplashScreenPreviewNetworkError() {
    MukMapTheme {
        SplashScreen(
            state = SplashContract.State(
                restaurants = listOf(dummyRestaurant, dummyRestaurant, dummyRestaurant),
                networkLoading = false,
                errorStatus = SplashContract.State.ErrorStatus(errorMsg = "문제가 발생했습니다 다시 시도해 주세요"),
            ),
            effectFlow = flowOf(),
            onEventSent = {},
            onNavigationRequested = {}
        )
    }
}