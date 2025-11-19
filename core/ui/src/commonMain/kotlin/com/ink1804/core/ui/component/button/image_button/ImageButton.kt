package com.ink1804.core.ui.component.button.image_button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.ink1804.core.ui.component.button.ButtonStyle
import com.ink1804.core.ui.component.button.styled
import com.ink1804.core.ui.component.common.ComponentState
import com.ink1804.core.ui.util.LightRippleConfiguration
import com.ink1804.core.ui.util.clickWithRipple

@Composable
fun ImageButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit,
    style: ButtonStyle = ButtonStyle.Primary,
    state: ComponentState = ComponentState.Enabled,
) {
    CompositionLocalProvider(LocalRippleConfiguration provides LightRippleConfiguration) {
        Box(
            modifier = modifier
                .size(44.dp)
                .background(
                    color = style.backgroundColor.styled(style, state),
                    shape = style.shape
                )
                .border(
                    width = 1.dp, color = style.borderColor.styled(style, state),
                    shape = style.shape
                )
                .clip(style.shape)
                .clickWithRipple { onClick.invoke() },
            contentAlignment = Alignment.Center,
        ) {
            if (state == ComponentState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(8.dp),
                    color = style.contentColor,
                    strokeWidth = 2.dp,
                    trackColor = style.contentColor.copy(alpha = 0.1f),
                )
            } else {
                Icon(
                    modifier = Modifier.padding(4.dp),
                    imageVector = imageVector,
                    contentDescription = null,
                    tint = style.contentColor.styled(style, state),
                )
            }
        }
    }
}
