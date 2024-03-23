package com.wamcstudios.calorytracker.onboarding.presentation.goal

import com.wamcstudios.calorytracker.core.domain.model.GoalType

sealed class GoalEvent {

    data class ChangeGoalTypeSelected(val goalType: GoalType) : GoalEvent()

    object OnNextClick : GoalEvent()
}