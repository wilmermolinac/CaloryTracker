package com.wamcstudios.calorytracker.onboarding.presentation.goal

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
class GoalViewModel @Inject constructor(private val onboardingUseCases: OnboardingUseCases) :
    ViewModel() {

    var state by mutableStateOf(GoalState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: GoalEvent) {
        when (event) {
            is GoalEvent.ChangeGoalTypeSelected -> {
                state = state.copy(goalTypeSelected = event.goalType)
            }

            GoalEvent.OnNextClick -> {

                viewModelScope.launch {
                    onboardingUseCases.saveGoalType(state.goalTypeSelected)
                    _uiEvent.send(UiEvent.Navigate(route = NavigationRoute.NutrientGoal.route))
                }

            }
        }
    }
}