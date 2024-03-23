package com.wamcstudios.calorytracker.onboarding.presentation.weight

sealed class WeightEvent {

    data class ChangeValueWeight(val value: String) : WeightEvent()

    object OnClickNext : WeightEvent()
}