package com.example.presentation.ui.splash

import android.util.Log
import com.example.presentation.base.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import team.pompom.mukmap.extension.domainResultFlatMapConcat
import team.pompom.mukmap.usecase.InitUseCase
import team.pompom.mukmap.usecase.RestaurantUseCase
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    initUseCase: InitUseCase,
    restaurantUseCase: RestaurantUseCase
) : BaseViewModel<SplashContract.Event, SplashContract.State, SplashContract.Effect>() {

    override fun setInitialState(): SplashContract.State {
        return SplashContract.State(
            restaurants = listOf(),
            networkLoading = true,
            networkError = false,
        )
    }

    override fun handleEvents(event: SplashContract.Event) {
        when (event) {
            SplashContract.Event.SuccessToGetRestaurant -> setEffect { SplashContract.Effect.Navigation.MoveToMain }
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
                            networkError = false
                        )
                    }
                },
                onError = {
                    it.printStackTrace()
                    Log.d("Ram test", "error, error data = $it")
                    setState {
                        copy(
                            restaurants = listOf(),
                            networkLoading = false,
                            networkError = true
                        )
                    }
                }
            )
    }
}