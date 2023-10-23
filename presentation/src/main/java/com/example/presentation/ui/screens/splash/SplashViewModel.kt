package com.example.presentation.ui.screens.splash

import android.util.Log
import com.example.presentation.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import team.pompom.mukmap.extension.domainResultFlatMapConcat
import team.pompom.mukmap.usecase.InitUseCase
import team.pompom.mukmap.usecase.RestaurantUseCase
import javax.inject.Inject
import javax.net.ssl.SSLHandshakeException

@HiltViewModel
class SplashViewModel @Inject constructor(
    initUseCase: InitUseCase,
    restaurantUseCase: RestaurantUseCase
) : BaseViewModel<SplashContract.Event, SplashContract.State, SplashContract.Effect>() {

    override fun setInitialState(): SplashContract.State {
        return SplashContract.State(
            restaurants = listOf(),
            networkLoading = true,
            errorStatus = null,
        )
    }

    override fun handleEvents(event: SplashContract.Event) {
        when (event) {
            SplashContract.Event.SuccessToGetRestaurant -> setEffect { SplashContract.Effect.Navigation.MoveToMain }
            SplashContract.Event.OnErrorInSplash -> setEffect { SplashContract.Effect.Navigation.FinishApp }
        }
    }

    init {
        initUseCase
            .getAppInitData("BaekMap")
            .domainResultFlatMapConcat { initEntity ->
                restaurantUseCase
                    .getRestaurants(initEntity.needToRefreshData, "BaekMap")
            }
            .viewModelsIn(
                onSuccess = {
                    Log.d("Ram test", "success, init data = $it")
                    restaurantUseCase.cachingRestaurants(it.restaurants)
                    setState {
                        copy(
                            restaurants = it.restaurants,
                            networkLoading = false,
                            errorStatus = null
                        )
                    }
                },
                onError = {
                    setState {
                        copy(
                            restaurants = listOf(),
                            networkLoading = false,
                            errorStatus = when (it) {
                                is SSLHandshakeException -> {
                                    SplashContract.State.ErrorStatus(errorMsg = "기기의 인터넷 인증서에 문제가 있습니다. 문제가 지속된다면 remin1994@gmail.com 으로 문의 주세요")
                                }
                                else -> {
                                    SplashContract.State.ErrorStatus(errorMsg = "문제가 발생했습니다 잠시 후 다시 시도해 주세요")
                                }
                            }
                        )
                    }
                    it.printStackTrace()
                    Log.d("Ram test", "error, error data = $it")
                }
            )
    }
}