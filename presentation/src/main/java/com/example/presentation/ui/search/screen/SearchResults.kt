package com.example.presentation.ui.search.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.theme.MukMapTheme
import com.example.presentation.theme.defaultDivider
import com.example.presentation.ui.base.dummyRestaurant
import com.example.presentation.ui.main.screen.RestaurantInfo
import team.pompom.mukmap.model.restaurants.RestaurantsEntity

@Composable
fun SearchResults(
    restaurants: List<RestaurantsEntity.Restaurant>,
    onClickRestaurant: (restaurant: RestaurantsEntity.Restaurant) -> Unit
) {
    LazyColumn {
        itemsIndexed(restaurants) { index, item ->
            RestaurantInfo(
                modifier = Modifier
                    .clickable { onClickRestaurant.invoke(item) }
                    .padding(vertical = 20.dp),
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
        SearchResults(
            restaurants = listOf(dummyRestaurant, dummyRestaurant, dummyRestaurant)
        ) {

        }
    }
}
