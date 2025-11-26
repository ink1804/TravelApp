package com.ink1804.feature.onboarding.api

data class OnboardingState(
    val index: Int = 0,
    val step: OnboardingStep = onboardingSteps[0],
    val allowBackNavigation: Boolean = index > 0,
    val showProgress: Boolean = true,
) {
    val progress: Float
        get() = index / onboardingSteps.size.toFloat()
}
