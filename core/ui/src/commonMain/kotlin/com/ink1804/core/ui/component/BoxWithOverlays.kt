package com.ink1804.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun BoxWithOverlays(
    modifier: Modifier = Modifier.fillMaxSize(),
    contentBehind: @Composable BoxScope.() -> Unit = {},
    contentAhead: @Composable BoxScope.() -> Unit = {},
    topOverlayFraction: Float = 0.2f,
    bottomOverlayFraction: Float = 0.4f,
) {
    Box(modifier = modifier) {
        contentBehind()

        DarkOverlay(
            modifier = Modifier.align(Alignment.TopCenter),
            fraction = topOverlayFraction,
            colors = listOf(Color(0xA6000000), Color.Transparent),
        )

        DarkOverlay(
            modifier = Modifier.align(Alignment.BottomCenter),
            fraction = bottomOverlayFraction,
            colors = listOf(Color.Transparent, Color(0xD6000000)),
        )

        contentAhead()
    }
}

@Composable
fun DarkOverlay(
    modifier: Modifier = Modifier,
    fraction: Float,
    colors: List<Color>,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction)
            .background(brush = Brush.verticalGradient(colors = colors))
    )
}
