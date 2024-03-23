package com.wamcstudios.calorytracker.onboarding.presentation.nutrient

sealed class NutrientEvent {

    data class ChangeValueCarbs(val value: String) : NutrientEvent()
    data class ChangeValueProteins(val value: String) : NutrientEvent()
    data class ChangeValueFats(val value: String) : NutrientEvent()

    object OnClickNext : NutrientEvent()
}