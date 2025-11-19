package com.ink1804.core.ui.component.button

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ink1804.core.ui.component.common.ComponentState

enum class ButtonStyle(
    val backgroundColor: Color,
    val contentColor: Color,
    val borderColor: Color,
    val shape: RoundedCornerShape
) {
    Primary(
        backgroundColor = Color.White,
        contentColor = Color.Black,
        borderColor = Color.Transparent,
        shape = RoundedCornerShape(12.dp),
    ),

    Secondary(
        backgroundColor = Color.Transparent,
        contentColor = Color.White,
        borderColor = Color.LightGray,
        shape = RoundedCornerShape(12.dp),
    ),

    Tertiary(
        backgroundColor = Color.Transparent,
        contentColor = Color.White,
        borderColor = Color.Transparent,
        shape = RoundedCornerShape(12.dp),
    ),
}

fun Color.styled(style: ButtonStyle, state: ComponentState): Color {
    return this.let {
        if (state == ComponentState.Disabled) {
            if (this != Color.Transparent) it.copy(alpha = 0.3f) else it
        } else {
            it
        }
    }
}
