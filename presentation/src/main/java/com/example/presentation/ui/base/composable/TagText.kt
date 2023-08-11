package com.example.presentation.ui.base.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.presentation.theme.MukMapTheme
import com.example.presentation.theme.contentFont
import com.example.presentation.theme.tagContent

@Composable
fun TagText(
    tags: List<String>
) {
    Text(
        text = tags.filter { it.isNotEmpty() }.joinToString(
            separator = " "
        ) { "#$it" },
        style = contentFont(fontSize = 14.sp),
        color = tagContent
    )
}

@Composable
@Preview(showBackground = true)
fun TagTextPreview() {
    MukMapTheme {
        TagText(tags = listOf("곡성", "콩국수", "EP01", "1화"))
    }
}