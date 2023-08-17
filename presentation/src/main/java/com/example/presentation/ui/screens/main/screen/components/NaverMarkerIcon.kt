package com.example.presentation.ui.screens.main.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.theme.MukMapTheme
import com.example.presentation.theme.titleFont
import com.example.presentation.ui.screens.common.composable.ImageWithTextSlot
import com.naver.maps.map.compose.ExperimentalNaverMapApi

@Composable
fun NaverMarkerIcon(
    modifier: Modifier = Modifier,
    restaurantName: String,
) {
    ImageWithTextSlot(
        modifier = modifier,
        imageArea = {
            Image(
                modifier = Modifier.size(width = 21.dp, height = 25.dp),
                painter = painterResource(id = R.drawable.ic_mark),
                contentDescription = "mark icon",
            )
        },
        textArea = {
            Text(
                text = restaurantName,
                style = titleFont(13.sp),
            )
        })
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
@Preview(showBackground = true)
fun NaverMarkerIconPreview() {
    MukMapTheme {
        NaverMarkerIcon(restaurantName = "청보석")
    }
}