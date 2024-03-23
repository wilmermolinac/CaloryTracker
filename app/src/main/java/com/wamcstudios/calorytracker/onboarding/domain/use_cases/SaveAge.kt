package com.wamcstudios.calorytracker.onboarding.domain.use_cases

import com.wamcstudios.calorytracker.core.domain.preferences.Preferences
import javax.inject.Inject

class SaveAge @Inject constructor(private val preferences: Preferences) {

    operator fun invoke(age: Int) {
        preferences.saveAge(age)
    }
}