package com.example.presentation.ui.main.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.theme.MukMapTheme
import com.example.presentation.theme.contentFont
import com.example.presentation.theme.primaryContent
import com.example.presentation.theme.titleFont
import com.example.presentation.theme.youtubeBackgroundInfo
import com.example.presentation.ui.base.dummyRestaurant
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
                Icon(
                    modifier = Modifier.clickable { restaurantDetailClickAction.naverButtonClicked() },
                    painter = painterResource(id = R.drawable.ic_naver),
                    contentDescription = "naverIcon"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
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
                        .background(Color.Black, RoundedCornerShape(20.dp))
                ) {
                    Text(text = "TODO 유튜브 영역")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    modifier = Modifier
                        .weight(7f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "님아 그 시장을 가오_EP. 01_곡성 ",
                        style = titleFont(fontSize = 12.sp),
                        color = primaryContent
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "“백종원이 누군디?” 백종원을 모르는 순수한 사장님의 찐한 콩국수집",
                        style = contentFont(fontSize = 12.sp),
                        color = primaryContent
                    )
                }
            }
        }
    }
}

interface RestaurantDetailClickAction {
    fun exitButtonClicked()
    fun naverButtonClicked()
    fun youtubeButtonClicked()
}

@Composable
@Preview(showBackground = true)
fun RestaurantDetailPreview() {
    MukMapTheme {
        RestaurantDetail(
            restaurant = dummyRestaurant,
            restaurantDetailClickAction = object : RestaurantDetailClickAction {
                override fun exitButtonClicked() {}

                override fun naverButtonClicked() {}

                override fun youtubeButtonClicked() {}
            }
        )
    }
}
