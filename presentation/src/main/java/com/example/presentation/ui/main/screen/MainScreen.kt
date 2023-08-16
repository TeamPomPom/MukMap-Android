package com.example.presentation.ui.main.screen

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.PointF
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.theme.MukMapTheme
import com.example.presentation.theme.titleFont
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


val SearchBarTopMargin = 14.dp
val SearchBarBottomMargin = 24.dp

@Composable
fun MainScreen(
    state: MainContract.State,
    effectFlow: Flow<MainContract.Effect>,
    onEventSent: (event: MainContract.Event) -> Unit,
    onNavigationFlow: (MainContract.Effect.Navigation) -> Unit
) {

    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val context = LocalContext.current
    val screenHeight = configuration.screenHeightDp
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    var userScrollEnabled by remember { mutableStateOf(false) }
    var isDetailRestaurantView by remember { mutableStateOf(false) }
    var expandedState by remember { mutableStateOf(ExpandedState.HALF) }
    var sizeOfSearchBar by remember { mutableStateOf(0.dp) }
    var sizeOfBottomSheetContentHalf by remember { mutableStateOf(screenHeight.dp) }
    var sizeOfBottomSheetContentCollapsed by remember { mutableStateOf(screenHeight.dp) }

    LaunchedEffect(key1 = true) {
        effectFlow.onEach { effect ->
            when (effect) {
                is MainContract.Effect.Navigation -> {
                    onNavigationFlow(effect)
                }
                MainContract.Effect.InitBottomSheetState -> {
                    expandedState = ExpandedState.COLLAPSED
                    isDetailRestaurantView = false
                }
                is MainContract.Effect.MoveToNaverMap -> {
                    moveToNaverMap(context = context, placeId = effect.placeId)
                }
            }
        }.collect()
    }

    LaunchedEffect(state.searchedRestaurant) {
        isDetailRestaurantView = state.searchedRestaurant != null
    }

    BottomSheet(
        expandedHeight = screenHeight.dp - (sizeOfSearchBar + SearchBarBottomMargin + SearchBarTopMargin) + statusBarHeight,
        halfExpandedHeight = sizeOfBottomSheetContentHalf + statusBarHeight,
        collapsedHeight = sizeOfBottomSheetContentCollapsed + statusBarHeight,
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
                    onEventSent.invoke(MainContract.Event.RefreshSearchedRestaurant)
                }

                Box(
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(top = SearchBarTopMargin, start = 24.dp, end = 24.dp)
                        .onGloballyPositioned { coordinates ->
                            with(density) { sizeOfSearchBar = coordinates.size.height.toDp() }
                        },
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
                    Box(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, top = 24.dp, bottom = 44.dp)
                            .navigationBarsPadding()
                    ) {
                        RestaurantDetail(
                            restaurant = restaurant,
                            restaurantDetailClickAction = object : RestaurantDetailClickAction {
                                override fun exitButtonClicked() {
                                    onEventSent.invoke(MainContract.Event.RefreshSearchedRestaurant)
                                }

                                override fun naverButtonClicked(placeId: String) {
                                    onEventSent.invoke(MainContract.Event.NaverButtonClicked(placeId))
                                }

                                override fun youtubeButtonClicked() {

                                }
                            }
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .onGloballyPositioned { coordinates ->
                            with(density) {
                                when (expandedState) {
                                    ExpandedState.HALF -> { if (sizeOfBottomSheetContentHalf == screenHeight.dp) sizeOfBottomSheetContentHalf = coordinates.size.height.toDp() }
                                    ExpandedState.COLLAPSED -> { if (sizeOfBottomSheetContentCollapsed == screenHeight.dp) sizeOfBottomSheetContentCollapsed = coordinates.size.height.toDp() }
                                    ExpandedState.FULL -> { }
                                }
                            }
                        }
                ) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(text = "최근에 방영된 음식점", style = titleFont(20.sp))
                    Spacer(modifier = Modifier.height(20.dp))
                    RestaurantInfoList(
                        onFirstItemTop = {
                            if (expandedState == ExpandedState.FULL) {
                                expandedState = ExpandedState.HALF
                                userScrollEnabled = false
                            }
                        },
                        userScrollEnabled = userScrollEnabled,
                        restaurants = when (expandedState) {
                            ExpandedState.HALF -> state.entireRestaurant.take(2)
                            ExpandedState.FULL -> state.entireRestaurant
                            ExpandedState.COLLAPSED -> state.entireRestaurant.take(0)
                        }
                    ) { restaurant ->
                        onEventSent.invoke(MainContract.Event.ClickRestaurant(restaurant))
                    }
                }
            }
        })
}

private fun moveToNaverMap(
    context: Context,
    placeId: String,
) {
    val url = "nmap://place?id=$placeId&appname=team.pompom.mukmap"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    intent.addCategory(Intent.CATEGORY_BROWSABLE)

    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=com.nhn.android.nmap")
            )
        )
    }
}

@Composable
@OptIn(ExperimentalNaverMapApi::class)
fun MapScreen(
    restaurants: List<RestaurantsEntity.Restaurant>,
    selectedRestaurant: RestaurantsEntity.Restaurant?,
    onMarkerClicked: (RestaurantsEntity.Restaurant) -> Unit,
    onMapClicked: (PointF, LatLng) -> Unit,
) {
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