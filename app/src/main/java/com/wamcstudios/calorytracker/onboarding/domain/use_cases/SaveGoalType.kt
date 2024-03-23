package com.wamcstudios.calorytracker.onboarding.domain.use_cases

import com.wamcstudios.calorytracker.core.domain.model.GoalType
import com.wamcstudios.calorytracker.core.domain.preferences.Preferences
import javax.inject.Inject

class SaveGoalType @Inject constructor(private val preferences: Preferences) {
    operator fun invoke(goalType: GoalType) {
        preferences.saveGoalType(goalType)
    }
}