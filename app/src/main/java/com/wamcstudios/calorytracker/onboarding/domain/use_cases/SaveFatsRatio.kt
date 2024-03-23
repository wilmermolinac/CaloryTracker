package com.wamcstudios.calorytracker.onboarding.domain.use_cases

import com.wamcstudios.calorytracker.core.domain.preferences.Preferences
import javax.inject.Inject

class SaveFatsRatio @Inject constructor(private val preferences: Preferences) {

    operator fun invoke(fats: Float) {
        preferences.saveFatRatio(fats)
    }
}