package com.example.presentation.ui.screens.common.previewparameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.presentation.ui.screens.common.composable.button.MukMapButtonStyle

class MukMapButtonStylePreviewParameterProvider : PreviewParameterProvider<MukMapButtonStyle> {
    override val values = sequenceOf(
        MukMapButtonStyle.Default,
        MukMapButtonStyle.OrangeSmall(),
        MukMapButtonStyle.GraySmall(),
    )
}