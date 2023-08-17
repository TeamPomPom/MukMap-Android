package com.example.presentation.ui.screens.main.screen.components

import android.graphics.PointF
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.presentation.R
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.compose.rememberMarkerState
import com.naver.maps.map.overlay.OverlayImage
import team.pompom.mukmap.model.restaurants.RestaurantsEntity

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