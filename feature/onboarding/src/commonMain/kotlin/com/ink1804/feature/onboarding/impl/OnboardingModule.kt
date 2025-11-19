package com.ink1804.feature.onboarding.impl

import com.ink1804.feature.onboarding.OnboardingComponent
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val onboardingModule = module {
    factoryOf(OnboardingComponentImpl::Factory) bind OnboardingComponent.Factory::class
}
