package com.wamcstudios.calorytracker.onboarding.presentation.height

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wamcstudios.calorytracker.R
import com.wamcstudios.calorytracker.core.domain.use_case.CoreUseCases
import com.wamcstudios.calorytracker.core.utils.UiText
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
class HeightViewModel @Inject constructor(
    private val coreUseCases: CoreUseCases,
    private val onboardingUseCases: OnboardingUseCases,
) : ViewModel() {

    var state by mutableStateOf(HeightState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: HeightEvent) {
        when (event) {
            is HeightEvent.ChangeValueHeight -> {
                if (event.value.length <= 3) {
                    val height = coreUseCases.filterOutDigits(event.value)
                    state = state.copy(heightValue = height)
                }


            }

            HeightEvent.OnClickNext -> {
                viewModelScope.launch {

                    if (state.heightValue.isBlank()) {
                        _uiEvent.send(UiEvent.ShowSnackBar(message = UiText.StringResource(R.string.error_height_cant_be_empty)))

                        return@launch
                    } else {
                        onboardingUseCases.saveHeight(state.heightValue.toInt())
                        _uiEvent.send(UiEvent.Navigate(route = NavigationRoute.Weight.route))
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