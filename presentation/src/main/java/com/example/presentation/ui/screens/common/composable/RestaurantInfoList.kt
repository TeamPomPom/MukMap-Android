package com.example.presentation.ui.screens.common.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.base.extension.conditional
import com.example.presentation.theme.MukMapTheme
import com.example.presentation.theme.defaultDivider
import com.example.presentation.ui.screens.common.previewparameter.dummyRestaurant
import com.example.presentation.ui.screens.main.screen.components.RestaurantInfo
import team.pompom.mukmap.model.restaurants.RestaurantsEntity

@Composable
fun RestaurantInfoList(
    modifier: Modifier = Modifier,
    userScrollEnabled: Boolean = true,
    restaurants: List<RestaurantsEntity.Restaurant>,
    onFirstItemTop: () -> Unit = { },
    onClickRestaurant: (restaurant: RestaurantsEntity.Restaurant) -> Unit
) {
    val listState = rememberLazyListState()
    val firstVisibleItemScrollOffset = remember { derivedStateOf { listState.firstVisibleItemScrollOffset } }

    var isOnTop by remember { mutableStateOf(false) }

    val nested = object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            val delta = available.y
            if (delta > 0 && isOnTop) onFirstItemTop()
            return super.onPreScroll(available, source)
        }
    }

    LazyColumn(
        state = listState,
        modifier = modifier
            .conditional(userScrollEnabled) {
                nestedScroll(nested)
            },
        userScrollEnabled = userScrollEnabled
    ) {
        itemsIndexed(restaurants) { index, item ->
            if (index == 0) {
                isOnTop = firstVisibleItemScrollOffset.value == 0
            }
            RestaurantInfo(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClickRestaurant.invoke(item) }
                    .padding(bottom = 20.dp, top = if (index == 0) 0.dp else 16.dp),
                restaurant = item
            )
            if (index < restaurants.lastIndex) Divider(thickness = 1.dp, color = defaultDivider)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SearchResultsPreview() {
    MukMapTheme {
        RestaurantInfoList(
            restaurants = listOf(dummyRestaurant, dummyRestaurant, dummyRestaurant)
        ) {

        }
    }
}
