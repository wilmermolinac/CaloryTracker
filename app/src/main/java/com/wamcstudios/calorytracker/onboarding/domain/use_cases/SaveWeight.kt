package com.wamcstudios.calorytracker.onboarding.domain.use_cases

import com.wamcstudios.calorytracker.core.domain.preferences.Preferences
import javax.inject.Inject

class SaveWeight @Inject constructor(private val preferences: Preferences) {

    operator fun invoke(weight: Float) {
        preferences.saveWeight(weight = weight)
    }
}