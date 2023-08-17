package com.example.presentation.ui.screens.splash.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.theme.MukMapTheme
import com.example.presentation.ui.screens.common.composable.ImageWithTextSlot

@Composable
fun SplashLoading() {
    ImageWithTextSlot(
        imagePadding = PaddingValues(
            horizontal = (12.5).dp,
            vertical = (10.56).dp
        ),
        imageArea = {
            Image(
                painter = painterResource(id = R.drawable.ic_mark),
                contentDescription = "splashIcon"
            )
        },
        textArea = {
            Text(text = stringResource(id = R.string.splash_main_title))
        })
}

@Composable
@Preview(showBackground = true)
fun SplashLoadingPreview() {
    MukMapTheme {
        SplashLoading()
    }
}