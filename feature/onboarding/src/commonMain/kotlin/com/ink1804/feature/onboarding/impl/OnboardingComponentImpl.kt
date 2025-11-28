package com.ink1804.feature.onboarding.impl

import com.arkivanov.decompose.ComponentContext
import com.ink1804.core.coroutines.createCoroutineScope
import com.ink1804.data.auth.AuthRepository
import com.ink1804.feature.onboarding.api.OnboardingComponent
import com.ink1804.feature.onboarding.api.OnboardingState
import com.ink1804.feature.onboarding.api.onboardingSteps
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class OnboardingComponentImpl(
    componentContext: ComponentContext,
    private val authRepository: AuthRepository,
    private val onFinished: () -> Unit,
) : OnboardingComponent {

    private val _state = MutableStateFlow(OnboardingState())
    override val state = _state.asStateFlow()

    private val scope = componentContext.createCoroutineScope()

    private val _isAuthorized = authRepository.isAuthorized()
        .stateIn(scope, started = SharingStarted.Eagerly, initialValue = false)

    init {
        scope.launch {
            _isAuthorized.collectLatest {
                if (it) {
                    onNext()
                }
            }
        }
    }


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

    class Factory(
        private val authRepository: AuthRepository,
    ) : OnboardingComponent.Factory {
        override fun invoke(context: ComponentContext, onFinished: () -> Unit): OnboardingComponent = OnboardingComponentImpl(
            componentContext = context,
            authRepository = authRepository,
            onFinished = onFinished,
        )
    }
}
