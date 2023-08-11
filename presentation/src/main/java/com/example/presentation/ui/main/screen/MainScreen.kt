package com.example.presentation.ui.main.screen

import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import com.example.presentation.ui.base.util.BitmapFromComposable
import com.example.presentation.ui.main.MainContract
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.NaverMap
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

    LaunchedEffect(key1 = true) {
        effectFlow.onEach { effect ->
            when (effect) {
                is MainContract.Effect.Navigation -> {
                    onNavigationFlow(effect)
                }

                is MainContract.Effect.MoveMapToRestaurant -> {
                    effect.restaurant
                }
            }
        }.collect()
    }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp

    BottomSheet(
        expandedHeight = screenHeight,
        halfExpandedHeight = screenHeight / 2,
        collapsedHeight = 100,
        stateChanged = { expandedState ->
            userScrollEnabled = when (expandedState) {
                ExpandedState.FULL -> true
                ExpandedState.COLLAPSED, ExpandedState.HALF -> false
            }
        },
        entireContent = {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                MapScreen(state.entireRestaurant)


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
            RestaurantInfoList(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 24.dp),
                userScrollEnabled = userScrollEnabled,
                restaurants = state.entireRestaurant
            ) {
                onEventSent.invoke(MainContract.Event.ClickRestaurant(it))
            }
        })
}

@Composable
@OptIn(ExperimentalNaverMapApi::class)
fun MapScreen(
    list: List<RestaurantsEntity.Restaurant>
) {
    val bitMapList = remember { mutableStateListOf<Pair<Bitmap, RestaurantsEntity.Restaurant>>() }
    val context = LocalContext.current

    list.forEach { restaurant ->
        BitmapFromComposable(targetComposable = {
            NaverMarkerIcon(modifier = Modifier, restaurantName = restaurant.name ?: "")
        }, whenBitmapCreated = { bitmap ->
            bitMapList.add(bitmap to restaurant)
        })
    }

    NaverMap(modifier = Modifier.fillMaxSize()) {
        bitMapList.forEach {
            Marker(
                state = rememberMarkerState(
                    position = LatLng(it.second.lat ?: 0.0, it.second.lng ?: 0.0)
                ),
                icon = OverlayImage.fromResource(R.drawable.ic_mark),
                captionText = it.second.name,
                onClick = { marker ->
                    Toast.makeText(context, it.second.name, Toast.LENGTH_LONG).show()
                    marker
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