package com.wamcstudios.calorytracker.onboarding.presentation.activity

import com.wamcstudios.calorytracker.core.domain.model.ActivityLevel

sealed class ActivityEvent {

    data class ChangeValueActivityLevelSelected(val activityLevel: ActivityLevel) : ActivityEvent()

    object OnNextClick : ActivityEvent()
}