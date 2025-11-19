package com.ink1804.feature.onboarding.impl

import com.arkivanov.decompose.ComponentContext
import com.ink1804.feature.onboarding.OnboardingComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class OnboardingComponentImpl(
    componentContext: ComponentContext,
    private val onFinished: () -> Unit,
) : OnboardingComponent {

    private val _state = MutableStateFlow(OnboardingState())
    override val state = _state.asStateFlow()

    override fun onNext() {
        val i = _state.value.index
        if (i == onboardingSteps.lastIndex) {
            onFinished()
        } else {
            updateIndex(i + 1)
        }
    }

    override fun onBack() {
        val i = _state.value.index
        if (i > 0) updateIndex(i - 1)
    }

    private fun updateIndex(newIndex: Int) {
        _state.update {
            it.copy(
                index = newIndex,
                step = onboardingSteps[newIndex],
                allowBackNavigation = newIndex > 0,
                showProgress = true,
            )
        }
    }

    class Factory() : OnboardingComponent.Factory {
        override fun invoke(context: ComponentContext, onFinished: () -> Unit): OnboardingComponent = OnboardingComponentImpl(
            componentContext = context,
            onFinished = onFinished,
        )
    }
}

data class OnboardingState(
    val index: Int = 0,
    val step: OnboardingStep = onboardingSteps[0],
    val allowBackNavigation: Boolean = index > 0,
    val showProgress: Boolean = true,
) {
    val progress: Float
        get() = index / onboardingSteps.size.toFloat()
}

