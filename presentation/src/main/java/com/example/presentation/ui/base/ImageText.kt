package com.example.presentation.ui.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun ImageWithTextSlot(
    imagePadding: PaddingValues = PaddingValues(),
    imageArea: @Composable () -> Unit,
    textArea: @Composable () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.padding(imagePadding)) {
            imageArea()
        }
        textArea()
    }
}