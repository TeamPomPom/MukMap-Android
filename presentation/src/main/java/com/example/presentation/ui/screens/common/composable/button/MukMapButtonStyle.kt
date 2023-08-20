package com.example.presentation.ui.screens.common.composable.button

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.presentation.theme.accentColor
import com.example.presentation.theme.grayBackground
import com.example.presentation.theme.semiTitleFont
import com.example.presentation.theme.titleContent
import com.example.presentation.theme.titleFont

sealed class MukMapButtonStyle(
    open val textColor: Color = Color.White,
    open val textSize: Int = DEFAULT_TEXT_SIZE,
    open val textStyle: TextStyle = titleFont(textSize.sp),
    open val backgroundColor: Color = accentColor,
    open val shapeRadius: Int = 18,
) {

    companion object {
        const val DEFAULT_TEXT_SIZE = 14
    }

    object Default : MukMapButtonStyle()

    class OrangeSmall(
        override val textSize: Int = DEFAULT_TEXT_SIZE,
        override val textStyle: TextStyle = semiTitleFont(textSize.sp)
    ) : MukMapButtonStyle()

    class GraySmall(
        override val textSize: Int = DEFAULT_TEXT_SIZE,
        override val textStyle: TextStyle = semiTitleFont(textSize.sp),
        override val textColor: Color = titleContent,
        override val backgroundColor: Color = grayBackground
    ) : MukMapButtonStyle()
}