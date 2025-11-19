package com.ink1804.core.ui.util

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput

fun Modifier.bounceClickable(
    pressedScale: Number = 0.9f,
    onClick: (() -> Unit)? = null,
) = composed {
    var isPressed by remember { mutableStateOf(false) }
    val floatScale = pressedScale.toFloat()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) floatScale else 1f,
        label = ""
    )

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .pointerInput(Unit) {
            detectTapGestures(
                onPress = {
                    isPressed = true
                    val success = tryAwaitRelease()
                    if (success && isPressed) {
                        onClick?.invoke()
                    }
                    isPressed = false
                }
            )
        }
}

fun Modifier.clickWithRipple(onClick: () -> Unit): Modifier = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = ripple(),
        onClick = onClick
    )
}




//ref: https://blog.canopas.com/jetpack-compose-cool-button-click-effects-c6bbecec7bcb
//enum class ButtonState { Pressed, Idle }
//
//fun Modifier.bounceClick(scale: Float = 0.9f) = composed {
//    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
//    val scaleState by animateFloatAsState(if (buttonState == ButtonState.Pressed) scale else 1f, label = "")
//
//    this
//        .graphicsLayer {
//            scaleX = scaleState
//            scaleY = scaleState
//        }
//        .clickable(
//            interactionSource = remember { MutableInteractionSource() },
//            indication = null,
//            onClick = { }
//        )
//        .pointerInput(buttonState) {
//            awaitPointerEventScope {
//                buttonState = if (buttonState == ButtonState.Pressed) {
//                    waitForUpOrCancellation()
//                    ButtonState.Idle
//                } else {
//                    awaitFirstDown(false)
//                    ButtonState.Pressed
//                }
//            }
//        }
//}