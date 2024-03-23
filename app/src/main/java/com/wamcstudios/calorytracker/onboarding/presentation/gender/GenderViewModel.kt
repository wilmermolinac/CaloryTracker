package com.wamcstudios.calorytracker.onboarding.presentation.gender

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wamcstudios.calorytracker.navigation.routes.NavigationRoute
import com.wamcstudios.calorytracker.navigation.utils.UiEvent
import com.wamcstudios.calorytracker.onboarding.domain.use_cases.OnboardingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(private val onboardingUseCases: OnboardingUseCases) :
    ViewModel() {

    var state by mutableStateOf(GenderState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: GenderEvent) {
        when (event) {
            is GenderEvent.ChangeGenderSelected -> {
                state = state.copy(genderSelected = event.gender)
            }

            GenderEvent.OnCLickNext -> {

                viewModelScope.launch {
                    onboardingUseCases.saveGender(state.genderSelected)
                    _uiEvent.send(UiEvent.Navigate(route = NavigationRoute.Age.route))
                }

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


}