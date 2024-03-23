package com.wamcstudios.calorytracker.onboarding.presentation.activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wamcstudios.calorytracker.navigation.routes.NavigationRoute
import com.wamcstudios.calorytracker.navigation.utils.UiEvent
import com.wamcstudios.calorytracker.onboarding.domain.use_cases.OnboardingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(private val onboardingUseCases: OnboardingUseCases) :
    ViewModel() {

    var state by mutableStateOf(ActivityState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ActivityEvent) {
        when (event) {
            is ActivityEvent.ChangeValueActivityLevelSelected -> {
                state = state.copy(activityLevelSelected = event.activityLevel)
            }

            ActivityEvent.OnNextClick -> {

                viewModelScope.launch {
                    onboardingUseCases.saveActivityLevel(state.activityLevelSelected)
                    _uiEvent.send(UiEvent.Navigate(route = NavigationRoute.Goal.route))
                }

            }
        }

    }
}