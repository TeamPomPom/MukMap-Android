package com.example.presentation.ui.screens.common.composable.errordialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.presentation.theme.defaultBackground

@Composable
fun PopupDialogSlot(
    modifier: Modifier = Modifier,
    showDialog: Boolean = true,
    onDismissRequest: () -> Unit,
    popUpDetailArea: @Composable () -> Unit,
    popUpButtonArea: @Composable ColumnScope.() -> Unit,
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismissRequest
        ) {
            Column(
                modifier = modifier
                    .size(width = 364.dp, height = 240.dp)
                    .wrapContentWidth()
                    .background(defaultBackground, RoundedCornerShape(20.dp))
                    .padding(vertical = 20.dp, horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    popUpDetailArea()
                }
                popUpButtonArea()
            }
        }
    }
}