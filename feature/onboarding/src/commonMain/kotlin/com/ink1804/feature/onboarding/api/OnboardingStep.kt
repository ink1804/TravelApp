package com.ink1804.feature.onboarding.api

sealed class OnboardingStep(val key: String) {
    object Welcome : OnboardingStep("onboarding_welcome")
    object ChooseLanguage : OnboardingStep("onboarding_choose_language")
    object ChooseTheme : OnboardingStep("onboarding_choose_theme")
    object Summary : OnboardingStep("onboarding_summary")
}

internal val onboardingSteps = listOf(
    OnboardingStep.Welcome,
    OnboardingStep.ChooseLanguage,
    OnboardingStep.ChooseTheme,
    OnboardingStep.Summary,
)
