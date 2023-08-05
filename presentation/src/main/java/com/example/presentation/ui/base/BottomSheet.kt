package com.example.presentation.ui.base

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

/**
 * ref : https://stackoverflow.com/a/74042822
 */
enum class ExpandedType {
    HALF, FULL, COLLAPSED
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    expandedHeight: Int,
    halfExpandedHeight: Int,
    collapsedHeight: Int,
    sheetShape: Shape = RoundedCornerShape(
        bottomStart = 0.dp,
        bottomEnd = 0.dp,
        topStart = 12.dp,
        topEnd = 12.dp
    ),
    entireContent: @Composable () -> Unit,
    bottomSheetContent: @Composable () -> Unit,
) {
    var expandedType by remember { mutableStateOf(ExpandedType.COLLAPSED) }

    val height by animateIntAsState(
        when (expandedType) {
            ExpandedType.HALF -> halfExpandedHeight
            ExpandedType.FULL -> expandedHeight
            ExpandedType.COLLAPSED -> collapsedHeight
        }, label = ""
    )

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetElevation = 8.dp,
        sheetShape = sheetShape,
        sheetContent = {
            var isUpdated = false
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(height.dp)
                    .pointerInput(Unit) {
                        detectVerticalDragGestures(
                            onVerticalDrag = { change, dragAmount ->
                                change.consume()
                                if (!isUpdated) {
                                    expandedType = when {
                                        dragAmount < 0 && expandedType == ExpandedType.COLLAPSED -> { ExpandedType.HALF }
                                        dragAmount < 0 && expandedType == ExpandedType.HALF -> { ExpandedType.FULL }
                                        dragAmount > 0 && expandedType == ExpandedType.FULL -> { ExpandedType.HALF }
                                        dragAmount > 0 && expandedType == ExpandedType.HALF -> { ExpandedType.COLLAPSED }
                                        else -> { ExpandedType.FULL }
                                    }
                                    isUpdated = true
                                }
                            },
                            onDragEnd = { isUpdated = false }
                        )
                    }
            ) {
                bottomSheetContent()
            }
        },
        sheetPeekHeight = height.dp
    ) {
        entireContent()
    }
}