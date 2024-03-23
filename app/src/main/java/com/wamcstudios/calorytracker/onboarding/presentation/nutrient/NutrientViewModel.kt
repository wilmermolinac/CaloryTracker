package com.wamcstudios.calorytracker.onboarding.presentation.nutrient

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wamcstudios.calorytracker.R
import com.wamcstudios.calorytracker.core.domain.use_case.CoreUseCases
import com.wamcstudios.calorytracker.core.domain.use_case.ValidateNutrients
import com.wamcstudios.calorytracker.core.utils.UiText
import com.wamcstudios.calorytracker.navigation.routes.NavigationGraphRoute
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
class NutrientViewModel @Inject constructor(
    private val coreUseCases: CoreUseCases,
    private val onboardingUseCases: OnboardingUseCases,
) : ViewModel() {

    var state by mutableStateOf(NutrientState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: NutrientEvent) {
        when (event) {
            is NutrientEvent.ChangeValueCarbs -> {

                if (event.value.length <= 2) {
                    val carbs = coreUseCases.filterOutDigits(event.value)
                    state = state.copy(carbsValue = carbs)
                }


            }

            is NutrientEvent.ChangeValueFats -> {
                if (event.value.length <= 2) {
                    val proteins = coreUseCases.filterOutDigits(event.value)
                    state = state.copy(fatsValue = proteins)
                }

            }

            is NutrientEvent.ChangeValueProteins -> {

                if (event.value.length <= 2) {
                    val fats = coreUseCases.filterOutDigits(event.value)
                    state = state.copy(proteinsValue = fats)
                }

            }

            NutrientEvent.OnClickNext -> {

                viewModelScope.launch {

                    coreUseCases.validateNutrients(
                        carbsRatio = state.carbsValue,
                        proteinsRatio = state.proteinsValue,
                        fatsRatio = state.fatsValue
                    ).let { validateNutrientsResult ->

                        when (validateNutrientsResult) {
                            is ValidateNutrients.ValidateNutrientsResult.Error -> {

                                _uiEvent.send(UiEvent.ShowSnackBar(message = validateNutrientsResult.message))

                                return@launch

                            }

                            is ValidateNutrients.ValidateNutrientsResult.Success -> {

                                onboardingUseCases.saveCarbsRatio(validateNutrientsResult.carbsRatio)
                                onboardingUseCases.saveProteinsRatio(validateNutrientsResult.proteinsRatio)
                                onboardingUseCases.saveFatsRatio(validateNutrientsResult.fatsRatio)
                                _uiEvent.send(UiEvent.Navigate(route = NavigationGraphRoute.TrackerGraph.route))

                            }

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