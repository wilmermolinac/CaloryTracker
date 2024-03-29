package com.wamcstudios.calorytracker.onboarding.domain.use_cases

import com.wamcstudios.calorytracker.core.domain.preferences.Preferences
import javax.inject.Inject

class LoadShouldShowOnboarding @Inject constructor(private val preferences: Preferences) {

    operator fun invoke(): Boolean {
        return preferences.loadShouldShowOnboarding()
    }
}