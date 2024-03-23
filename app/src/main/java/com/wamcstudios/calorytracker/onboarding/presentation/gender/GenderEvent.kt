package com.wamcstudios.calorytracker.onboarding.presentation.gender

import com.wamcstudios.calorytracker.core.domain.model.Gender

sealed class GenderEvent {

    data class ChangeGenderSelected(val gender: Gender) : GenderEvent()

    object OnCLickNext : GenderEvent()
}