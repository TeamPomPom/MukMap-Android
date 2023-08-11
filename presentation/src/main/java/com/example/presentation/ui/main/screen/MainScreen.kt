package com.example.presentation.ui.main.screen

import android.graphics.PointF
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.theme.MukMapTheme
import com.example.presentation.ui.base.composable.BottomSheet
import com.example.presentation.ui.base.composable.ExpandedState
import com.example.presentation.ui.base.composable.RestaurantInfoList
import com.example.presentation.ui.base.composable.SearchBar
import com.example.presentation.ui.base.constants.dummyRestaurant
import com.example.presentation.ui.main.MainContract
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.compose.rememberMarkerState
import com.naver.maps.map.overlay.OverlayImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import team.pompom.mukmap.model.restaurants.RestaurantsEntity

@Composable
fun MainScreen(
    state: MainContract.State,
    effectFlow: Flow<MainContract.Effect>,
    onEventSent: (event: MainContract.Event) -> Unit,
    onNavigationFlow: (MainContract.Effect.Navigation) -> Unit
) {

    var userScrollEnabled by remember { mutableStateOf(false) }

    var isDetailRestaurantView by remember { mutableStateOf(false) }
    var expandedState by remember { mutableStateOf(ExpandedState.HALF) }

    LaunchedEffect(key1 = true) {
        effectFlow.onEach { effect ->
            when (effect) {
                is MainContract.Effect.Navigation -> {
                    onNavigationFlow(effect)
                }
            }
        }.collect()
    }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp

    LaunchedEffect(state.searchedRestaurant) {
        isDetailRestaurantView = state.searchedRestaurant != null
    }

    BottomSheet(
        expandedHeight = screenHeight,
        halfExpandedHeight = screenHeight / 2,
        collapsedHeight = 100,
        expandedState = expandedState,
        isHeightControlledByHeight = isDetailRestaurantView.not(),
        stateChanged = { state ->
            expandedState = state
            userScrollEnabled = when (state) {
                ExpandedState.FULL -> true
                ExpandedState.COLLAPSED, ExpandedState.HALF -> false
            }
        },
        entireContent = {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                MapScreen(
                    restaurants = state.entireRestaurant,
                    selectedRestaurant = state.searchedRestaurant,
                    onMarkerClicked = { restaurant ->
                        onEventSent.invoke(MainContract.Event.ClickRestaurant(restaurant = restaurant))
                    }
                ) { pointF, latLng ->
                    expandedState = ExpandedState.COLLAPSED
                    isDetailRestaurantView = false
                }

                Box(
                    modifier = Modifier
                        .padding(top = 14.dp, start = 24.dp, end = 24.dp)
                ) {
                    SearchBar(
                        modifier = Modifier
                            .clickable { onEventSent.invoke(MainContract.Event.ClickSearch) },
                        showBorder = false,
                        enabled = false,
                        readOnly = true,
                        hint = state.searchText,
                        onValueChanged = { }
                    )
                }
            }
        },
        bottomSheetContent = {
            if (isDetailRestaurantView) {
                state.searchedRestaurant?.let { restaurant ->
                    expandedState = ExpandedState.FULL
                    RestaurantDetail(
                        restaurant = restaurant,
                        restaurantDetailClickAction = object : RestaurantDetailClickAction {
                            override fun exitButtonClicked() {
                                isDetailRestaurantView = false
                                expandedState = ExpandedState.COLLAPSED
                            }

                            override fun naverButtonClicked() {

                            }

                            override fun youtubeButtonClicked() {

                            }
                        }
                    )
                }
            } else {
                RestaurantInfoList(
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp, top = 24.dp),
                    userScrollEnabled = userScrollEnabled,
                    restaurants = state.entireRestaurant
                ) { restaurant ->
                    onEventSent.invoke(MainContract.Event.ClickRestaurant(restaurant))
                }
            }
        })
}

@Composable
@OptIn(ExperimentalNaverMapApi::class)
fun MapScreen(
    restaurants: List<RestaurantsEntity.Restaurant>,
    selectedRestaurant: RestaurantsEntity.Restaurant?,
    onMarkerClicked: (RestaurantsEntity.Restaurant) -> Unit,
    onMapClicked: (PointF, LatLng) -> Unit,
) {
    val context = LocalContext.current

    val cameraPositionState = rememberCameraPositionState()

    if (selectedRestaurant != null) {
        cameraPositionState.move(
            CameraUpdate.toCameraPosition(
                CameraPosition(
                    LatLng(selectedRestaurant.lat ?: 0.0, selectedRestaurant.lng ?: 0.0),
                    11.0
                )
            )
        )
    }

    NaverMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        onMapClick = onMapClicked
    ) {
        restaurants.forEach { restaurant ->
            Marker(
                state = rememberMarkerState(
                    position = LatLng(restaurant.lat ?: 0.0, restaurant.lng ?: 0.0)
                ),
                icon = OverlayImage.fromResource(R.drawable.ic_mark),
                captionText = restaurant.name,
                onClick = { marker ->
                    onMarkerClicked.invoke(restaurant)
                    true
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    MukMapTheme {
        MainScreen(
            state = MainContract.State(
                searchedRestaurant = null,
                entireRestaurant = listOf(dummyRestaurant, dummyRestaurant, dummyRestaurant),
                searchText = "음식 메뉴, 지역을 검색 해 보세요"
            ),
            effectFlow = flowOf(),
            onEventSent = { },
            onNavigationFlow = { }
        )
    }
}