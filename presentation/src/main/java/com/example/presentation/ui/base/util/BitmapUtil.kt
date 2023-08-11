package com.example.presentation.ui.base.util

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun BitmapFromComposable(
    context: Context = LocalContext.current,
    targetComposable: @Composable () -> Unit,
    whenBitmapCreated: (Bitmap) -> Unit,
) {
    AndroidView(
        factory = {
            BitmapComposeView(
                context = context,
                onBitmapCreated = whenBitmapCreated
            ) { targetComposable() }
        }
    )
}

private class BitmapComposeView(
    context: Context,
    onBitmapCreated: (bitmap: Bitmap) -> Unit,
    private val targetComposable: @Composable () -> Unit,
) : LinearLayoutCompat(context) {

    init {
        val view = ComposeView(context).apply {
            visibility = View.GONE
            this@BitmapComposeView.addView(this)
            setContent { targetComposable() }
        }

        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val graphicUtils = GraphicUtils()
                val bitmap = graphicUtils.createBitmapFromView(view = view)
                onBitmapCreated(bitmap)
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }
}

