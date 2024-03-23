package com.wamcstudios.calorytracker.onboarding.presentation.age

sealed class AgeEvent {

    data class ChangeValueAge(val value: String) : AgeEvent()
    object OnClickNext : AgeEvent()
}