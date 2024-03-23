package com.wamcstudios.calorytracker.onboarding.presentation.goal

import com.wamcstudios.calorytracker.core.domain.model.GoalType

data class GoalState(val goalTypeSelected: GoalType = GoalType.KeepWeight)
