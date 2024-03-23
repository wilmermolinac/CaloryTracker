package com.wamcstudios.calorytracker.onboarding.presentation.height

sealed class HeightEvent {

    data class ChangeValueHeight(val value: String) : HeightEvent()

    object OnClickNext : HeightEvent()
}