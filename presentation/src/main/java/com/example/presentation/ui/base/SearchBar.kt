package com.example.presentation.ui.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.base.ui.conditional
import com.example.presentation.base.ui.drawColoredShadow
import com.example.presentation.theme.MukMapTheme
import com.example.presentation.theme.defaultBackground
import com.example.presentation.theme.searchAreaBorder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    showBorder: Boolean,
    value: String,
    onValueChanged: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .conditional(!showBorder) {
                drawColoredShadow(
                    color = Color.Black,
                    alpha = 0.1f,
                    shadowRadius = 5.dp,
                    borderRadius = 20.dp,
                    offsetX = 5.dp,
                    offsetY = 5.dp
                )
            }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .height(64.dp)
                .clip(RoundedCornerShape(20.dp))
                .fillMaxWidth()
                .background(defaultBackground),
            value = value,
            onValueChange = onValueChanged,
            singleLine = true,
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (!showBorder) Color.White else searchAreaBorder,
                disabledBorderColor = if (!showBorder) Color.White else searchAreaBorder,
                errorBorderColor = if (!showBorder) Color.White else searchAreaBorder,
                unfocusedBorderColor = if (!showBorder) Color.White else searchAreaBorder,
            ),
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "search icon"
                )
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SearchBarPreview() {
    var text by remember { mutableStateOf("") }
    MukMapTheme {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            SearchBar(true, text) {
                text = it
            }
            Spacer(modifier = Modifier.height(30.dp))
            SearchBar(false, text) {
                text = it
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}