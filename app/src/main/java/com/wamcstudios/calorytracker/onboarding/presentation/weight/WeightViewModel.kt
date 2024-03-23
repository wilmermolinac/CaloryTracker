package com.wamcstudios.calorytracker.onboarding.presentation.weight

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
class WeightViewModel @Inject constructor(
    private val coreUseCases: CoreUseCases,
    private val onboardingUseCases: OnboardingUseCases,
) : ViewModel() {

    var state by mutableStateOf(WeightState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: WeightEvent) {
        when (event) {
            is WeightEvent.ChangeValueWeight -> {

                if (event.value.length <= 5){
                    val weight = coreUseCases.filterOutDigitsDecimal(event.value)
                    state = state.copy(weightValue = weight)
                }

            }

            WeightEvent.OnClickNext -> {

                viewModelScope.launch {
                    if (state.weightValue.isBlank()) {

                        _uiEvent.send(UiEvent.ShowSnackBar(message = UiText.StringResource(R.string.error_weight_cant_be_empty)))
                        // Exit viewModelScope
                        return@launch

                    }


                    // If else
                    onboardingUseCases.saveWeight(state.weightValue.toFloat())
                    _uiEvent.send(UiEvent.Navigate(route = NavigationRoute.Activity.route))


                }


            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}