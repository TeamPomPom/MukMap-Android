package com.example.presentation.ui.base.composable

import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.presentation.base.ui.conditional

/**
 * ref : https://stackoverflow.com/a/74042822
 */
enum class ExpandedState {
    HALF, FULL, COLLAPSED
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    expandedHeight: Dp,
    halfExpandedHeight: Dp,
    collapsedHeight: Dp,
    expandedState: ExpandedState = ExpandedState.COLLAPSED,
    isHeightControlledByHeight: Boolean,
    stateChanged: (ExpandedState) -> Unit = {},
    sheetShape: Shape = RoundedCornerShape(
        bottomStart = 0.dp,
        bottomEnd = 0.dp,
        topStart = 12.dp,
        topEnd = 12.dp
    ),
    entireContent: @Composable () -> Unit,
    bottomSheetContent: @Composable (ExpandedState) -> Unit,
) {
    val height by animateDpAsState(
        when (expandedState) {
            ExpandedState.HALF -> halfExpandedHeight
            ExpandedState.FULL -> expandedHeight
            ExpandedState.COLLAPSED -> collapsedHeight
        }, label = ""
    )

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    LaunchedEffect(expandedState) {
        when (expandedState) {
            ExpandedState.FULL -> if (isHeightControlledByHeight.not()) {
                bottomSheetScaffoldState.bottomSheetState.expand()
            }
            ExpandedState.COLLAPSED -> if (isHeightControlledByHeight.not()) {
                bottomSheetScaffoldState.bottomSheetState.collapse()
            }
            ExpandedState.HALF -> { }
        }
    }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetElevation = 8.dp,
        sheetShape = sheetShape,
        sheetContent = {
            var isUpdated = false
            Box(
                Modifier
                    .fillMaxWidth()
                    .conditional(isHeightControlledByHeight) {
                        this.height(height)
                            .pointerInput(expandedState) {
                            detectVerticalDragGestures(
                                onVerticalDrag = { change, dragAmount ->
                                    change.consume()
                                    if (!isUpdated) {
                                        when {
                                            dragAmount < 0 && expandedState == ExpandedState.COLLAPSED -> { ExpandedState.HALF }
                                            dragAmount < 0 && expandedState == ExpandedState.HALF -> { ExpandedState.FULL }
                                            dragAmount > 0 && expandedState == ExpandedState.FULL -> { ExpandedState.HALF }
                                            dragAmount > 0 && expandedState == ExpandedState.HALF -> { ExpandedState.COLLAPSED }
                                            else -> { ExpandedState.FULL }
                                        }.also(stateChanged)
                                        isUpdated = true
                                    }
                                },
                                onDragEnd = { isUpdated = false }
                            )
                        }
                    }
            ) {
                bottomSheetContent(expandedState)
            }
        },
        sheetPeekHeight = if (isHeightControlledByHeight) height else 0.dp
    ) {
        entireContent()
    }
}