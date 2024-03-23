package com.wamcstudios.calorytracker.onboarding.presentation.age

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wamcstudios.calorytracker.core.domain.use_case.CoreUseCases
import com.wamcstudios.calorytracker.navigation.routes.NavigationRoute
import com.wamcstudios.calorytracker.navigation.utils.UiEvent
import com.wamcstudios.calorytracker.onboarding.domain.use_cases.OnboardingUseCases
import com.wamcstudios.calorytracker.onboarding.domain.use_cases.ValidateAge
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val onboardingUseCases: OnboardingUseCases,
    private val coreUseCases: CoreUseCases,
) :
    ViewModel() {

    var state by mutableStateOf(AgeState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AgeEvent) {
        when (event) {
            is AgeEvent.ChangeValueAge -> {
                if (event.value.length <= 3) {
                    // Filter only Digit Number
                    val age = coreUseCases.filterOutDigits(event.value)
                    state = state.copy(ageValue = age)
                }

            }

            AgeEvent.OnClickNext -> {
                viewModelScope.launch {
                    val result = onboardingUseCases.validateAge(state.ageValue)
                    when (result) {
                        is ValidateAge.ValidateAgeResult.IsInvalid -> {
                            _uiEvent.send(UiEvent.ShowSnackBar(message = result.message))
                            // Exit viewModelScope
                            return@launch
                        }

                        is ValidateAge.ValidateAgeResult.IsValid -> {
                            onboardingUseCases.saveAge(result.data.toInt())
                            _uiEvent.send(UiEvent.Navigate(route = NavigationRoute.Height.route))
                        }
                    }

                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}