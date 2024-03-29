package com.wamcstudios.calorytracker.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.wamcstudios.calorytracker.navigation.routes.NavigationGraphRoute
import com.wamcstudios.calorytracker.onboarding.domain.use_cases.OnboardingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val onboardingUseCases: OnboardingUseCases):ViewModel() {
    var startDestinationState by mutableStateOf<NavigationGraphRoute>(NavigationGraphRoute.OnboardingGraph)
        private set

    init {

        validateShouldShowOnboarding()

    }

    private fun validateShouldShowOnboarding() {
        if (onboardingUseCases.loadShouldShowOnboarding().not()) {
            startDestinationState = NavigationGraphRoute.TrackerGraph
        }
    }
}