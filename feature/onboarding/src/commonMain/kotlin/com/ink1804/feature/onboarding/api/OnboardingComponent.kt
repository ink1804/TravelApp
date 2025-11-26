package com.ink1804.feature.onboarding.api

import com.arkivanov.decompose.ComponentContext
import com.ink1804.feature.onboarding.api.OnboardingState
import kotlinx.coroutines.flow.StateFlow

interface OnboardingComponent {

    val state: StateFlow<OnboardingState>
    fun onNext()
    fun onBack()
    fun interface Factory {
        operator fun invoke(
            context: ComponentContext,
            onFinished: () -> Unit,
        ): OnboardingComponent
    }
}