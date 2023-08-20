package com.example.presentation.ui.screens.common.composable.errordialog

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.presentation.theme.MukMapTheme
import com.example.presentation.ui.screens.common.MukMapPreviews
import com.example.presentation.ui.screens.common.composable.button.MukMapButton
import com.example.presentation.ui.screens.common.composable.button.MukMapButtonStyle

@Composable
fun OneButtonPopUpDialog(
    contentsText: String,
    buttonText: String,
    onClickButton: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    var showDialog by remember { mutableStateOf(true) }

    PopupDialogSlot(
        popUpDetailArea = {
            Text(text = contentsText)
        },
        popUpButtonArea = {
            MukMapButton(
                modifier = Modifier
                    .fillMaxWidth(),
                buttonText = buttonText,
                mukMapButtonStyle = MukMapButtonStyle.Default,
                onClick = {
                    showDialog = false
                    onClickButton()
                }
            )
        },
        onDismissRequest = onDismissRequest
    )
}

@Composable
fun TwoButtonPopUpDialog(
    contentsText: String,
    firstButtonText: String,
    secondButtonText: String,
    onClickButton: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    var showDialog by remember { mutableStateOf(true) }

    PopupDialogSlot(
        showDialog = showDialog,
        onDismissRequest = onDismissRequest,
        popUpDetailArea = {
            Text(text = contentsText)
        },
        popUpButtonArea = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                MukMapButton(
                    modifier = Modifier.weight(1f),
                    buttonText = firstButtonText,
                    mukMapButtonStyle = MukMapButtonStyle.GraySmall(),
                    onClick = {
                        showDialog = false
                        onClickButton()
                    }
                )
                Spacer(
                    modifier = Modifier.width(16.dp)
                )
                MukMapButton(
                    modifier = Modifier.weight(1f),
                    buttonText = secondButtonText,
                    mukMapButtonStyle = MukMapButtonStyle.OrangeSmall(),
                    onClick = {
                        showDialog = false
                        onClickButton()
                    }
                )
            }
        }
    )
}

@Composable
@MukMapPreviews
fun OneButtonPopUpDialogPreview() {
    MukMapTheme {
        OneButtonPopUpDialog(
            contentsText = "타이틀은 한줄을 웬만하면 넘기지 맙시다",
            buttonText = "업데이트 하러가기",
            onClickButton = { }
        ) {

        }
    }
}

@Composable
@MukMapPreviews
fun TwoButtonPopUpDialogPreview() {
    MukMapTheme {
        TwoButtonPopUpDialog(
            contentsText = "타이틀은 한줄을 웬만하면 넘기지 맙시다",
            firstButtonText = "취소",
            secondButtonText = "확인",
            onClickButton = { },
        ) {

        }
    }
}