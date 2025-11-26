package com.ink1804.feature.onboarding.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ink1804.core.ui.component.BoxWithOverlays
import com.ink1804.core.ui.component.button.ButtonStyle
import com.ink1804.core.ui.component.button.image_button.ImageButton
import com.ink1804.core.ui.util.toPreviewState
import com.ink1804.feature.onboarding.api.OnboardingComponent
import com.ink1804.feature.onboarding.api.OnboardingState
import com.ink1804.feature.onboarding.api.OnboardingStep
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun OnboardingScreen(component: OnboardingComponent) {
    Content(
        screenState = component.state.collectAsStateWithLifecycle(),
        onNextClicked = component::onNext,
        onBackClicked = component::onBack,
    )
}

@Composable
fun Content(
    screenState: State<OnboardingState>,
    onNextClicked: () -> Unit,
    onBackClicked: () -> Unit,
) {
    val state = screenState.value
    BoxWithOverlays(
        modifier = Modifier.fillMaxSize(),
        contentBehind = {},
        contentAhead = {
            Box(
                modifier = Modifier,
            ) {
                OnboardingStepScreen(
                    state = state,
                    onNextClicked = onNextClicked,
                    onBackClicked = onBackClicked,
                )

                OnboardingProgress(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 16.dp, end = 16.dp)
                        .systemBarsPadding(),
                    state = state,
                    onBackClick = { /* todo */ },
                )
            }
        },
    )
}

@Composable
fun OnboardingStepScreen(
    state: OnboardingState,
    onNextClicked: () -> Unit,
    onBackClicked: () -> Unit,
) {
    AnimatedContent(
        modifier = Modifier,
        targetState = state,
        label = state.toString()
    ) { targetState ->
        when (targetState.step) {
            OnboardingStep.Welcome -> OnboardingWelcomeScreen(onNext = onNextClicked, text = "Welcome")
            OnboardingStep.ChooseLanguage -> OnboardingChooseLanguageScreen(onNext = onNextClicked, text = "ChooseLanguage")
            OnboardingStep.ChooseTheme -> OnboardingChooseThemeScreen(onNext = onNextClicked, text = "ChooseTheme")
            OnboardingStep.Summary -> OnboardingSummaryScreen(onNext = onNextClicked, text = "Summary")
        }
    }
}


@Composable
fun OnboardingProgress(
    modifier: Modifier = Modifier,
    state: OnboardingState,
    onBackClick: () -> Unit,
) {
    val progress = state.progress

    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "",
    )

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        val buttonWidth by animateDpAsState(
            targetValue = if (state.allowBackNavigation) 44.dp else 0.dp,
            animationSpec = tween(durationMillis = 300),
            label = "BackButtonWidth"
        )
        ImageButton(
            modifier = Modifier
                .height(44.dp)
                .width(buttonWidth),
            style = ButtonStyle.Tertiary,
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            onClick = {
                if (state.allowBackNavigation) {
                    onBackClick.invoke()
                }
            },
        )

        if (state.showProgress) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .padding(start = 8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                progress = { progressAnimation },
                color = Color(0xFFEEC3FD),
                trackColor = Color(0x1AFFFFFF),
                strokeCap = StrokeCap.Round,
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    Content(
        screenState = OnboardingState().toPreviewState(),
        onNextClicked = {},
        onBackClicked = {},
    )
}
