package com.wamcstudios.calorytracker.onboarding.domain.use_cases

import com.wamcstudios.calorytracker.core.domain.model.ActivityLevel
import com.wamcstudios.calorytracker.core.domain.preferences.Preferences
import javax.inject.Inject

class SaveActivityLevel @Inject constructor(private val preferences: Preferences) {
    operator fun invoke(activityLevel: ActivityLevel) {
        preferences.saveActivityLevel(activityLevel)
    }
}