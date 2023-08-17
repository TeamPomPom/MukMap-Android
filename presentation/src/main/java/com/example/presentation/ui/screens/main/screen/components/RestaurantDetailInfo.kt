package com.example.presentation.ui.screens.main.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.presentation.R
import com.example.presentation.theme.MukMapTheme
import com.example.presentation.theme.contentFont
import com.example.presentation.theme.primaryContent
import com.example.presentation.theme.titleFont
import com.example.presentation.theme.youtubeBackgroundInfo
import com.example.presentation.ui.screens.common.dummyRestaurant
import team.pompom.mukmap.model.restaurants.RestaurantsEntity

@Composable
fun RestaurantDetail(
    restaurant: RestaurantsEntity.Restaurant,
    restaurantDetailClickAction: RestaurantDetailClickAction
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            RestaurantInfo(
                modifier = Modifier.weight(1f, true),
                restaurant = restaurant
            )
            Row {
                Image(
                    modifier = Modifier.clickable { restaurant.naverPlaceId?.let { restaurantDetailClickAction.naverButtonClicked(it) } },
                    painter = painterResource(id = R.drawable.ic_naver),
                    contentDescription = "naverIcon"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    modifier = Modifier.clickable { restaurantDetailClickAction.exitButtonClicked() },
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "closeIcon",
                )
            }
        }
        Spacer(modifier = Modifier.height(22.dp))
        Box(
            modifier = Modifier
                .background(youtubeBackgroundInfo, RoundedCornerShape(20.dp))
                .height(105.dp)
                .padding(horizontal = 10.dp, vertical = 15.dp)
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .weight(3f)
                        .aspectRatio(1.25f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.Black)
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { restaurant.youtubeVideoId?.let { restaurantDetailClickAction.youtubeButtonClicked(it) } },
                        model = restaurant.youtubeThumbnail,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    modifier = Modifier
                        .weight(7f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    val (firstTitle, secondTitle) = restaurant.extractTitle()
                    if (firstTitle != null) {
                        Text(
                            text = firstTitle,
                            style = titleFont(fontSize = 12.sp),
                            color = primaryContent
                        )
                    }
                    if (secondTitle != null) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = secondTitle,
                            style = contentFont(fontSize = 12.sp),
                            color = primaryContent
                        )
                    }
                }
            }
        }
    }
}

private fun RestaurantsEntity.Restaurant.extractTitle(): Pair<String?, String?> {
    val firstTitle = youtubeTitle?.substringAfter("[")?.substringBefore("]")
    val secondTitle = youtubeTitle?.substringAfter("]")?.trim()
    return firstTitle to secondTitle
}

interface RestaurantDetailClickAction {
    fun exitButtonClicked()
    fun naverButtonClicked(placeId: String)
    fun youtubeButtonClicked(youtubeVideoId: String)
}

@Composable
@Preview(showBackground = true)
fun RestaurantDetailPreview() {
    MukMapTheme {
        RestaurantDetail(
            restaurant = dummyRestaurant,
            restaurantDetailClickAction = object : RestaurantDetailClickAction {
                override fun exitButtonClicked() {}

                override fun naverButtonClicked(placeId: String) { }

                override fun youtubeButtonClicked(youtubeVideoId: String) { }
            }
        )
    }
}
