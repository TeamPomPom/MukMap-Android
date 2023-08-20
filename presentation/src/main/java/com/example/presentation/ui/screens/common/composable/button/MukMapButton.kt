package com.example.presentation.ui.screens.common.composable.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.presentation.theme.MukMapTheme
import com.example.presentation.ui.screens.common.MukMapPreviews
import com.example.presentation.ui.screens.common.previewparameter.MukMapButtonStylePreviewParameterProvider

@Composable
fun MukMapButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    mukMapButtonStyle: MukMapButtonStyle,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(mukMapButtonStyle.shapeRadius.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = mukMapButtonStyle.backgroundColor,
            contentColor = mukMapButtonStyle.textColor,
        )
    ) {
        Text(
            text = buttonText,
            style = mukMapButtonStyle.textStyle
        )
    }
}

@Composable
@MukMapPreviews
fun MukMapButtonPreview(
    @PreviewParameter(MukMapButtonStylePreviewParameterProvider::class) mukMapButtonStyle: MukMapButtonStyle
) {
    MukMapTheme {
        MukMapButton(
            buttonText = "업데이트 하러가기",
            mukMapButtonStyle = mukMapButtonStyle,
        ) {

        }
    }
}