package com.example.presentation.ui.main.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.theme.MukMapTheme
import com.example.presentation.theme.restaurantMainCategory
import com.example.presentation.theme.contentFont
import com.example.presentation.theme.primaryContent
import com.example.presentation.theme.titleContent
import com.example.presentation.theme.titleFont
import com.example.presentation.ui.base.TagText
import com.example.presentation.ui.base.dummyRestaurant
import team.pompom.mukmap.model.restaurants.RestaurantsEntity

@Composable
fun RestaurantInfo(
    modifier: Modifier = Modifier,
    restaurant: RestaurantsEntity.Restaurant
) {
    Column(
        modifier = modifier
    ) {
        RestaurantTitle(
            restaurantName = restaurant.name ?: "",
            categoryTitle = "한식"
        )
        if (restaurant.fullAddress != null) {
            RestaurantAddress(restaurant.fullAddress)
        }
        RestaurantTags(restaurant = restaurant)
    }
}

@Composable
private fun RestaurantTags(
    restaurant: RestaurantsEntity.Restaurant
) {
    Spacer(modifier = Modifier.height(7.dp))
    TagText(
        tags = mutableListOf<String>().apply {
            add(restaurant.province ?: "")
            addAll(restaurant.subCategory ?: listOf())
            add("EP_${restaurant.episodeNum}")
            add("${restaurant.episodeNum}화")
        }
    )
}

@Composable
fun RestaurantTitle(
    restaurantName: String,
    categoryTitle: String,
) {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = restaurantName,
            style = titleFont(fontSize = 18.sp),
            color = titleContent
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = categoryTitle,
            style = contentFont(fontSize = 14.sp),
            color = restaurantMainCategory
        )
    }
}

@Composable
fun RestaurantAddress(address: String?) {
    address ?: return
    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = address,
        style = contentFont(fontSize = 14.sp),
        color = primaryContent
    )
}

@Composable
@Preview(showBackground = true)
fun RestaurantInfoPreview() {
    MukMapTheme {
        RestaurantInfo(restaurant = dummyRestaurant)
    }
}