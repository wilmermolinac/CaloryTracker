package com.wamcstudios.calorytracker.onboarding.domain.use_cases

import com.wamcstudios.calorytracker.core.domain.model.Gender
import com.wamcstudios.calorytracker.core.domain.preferences.Preferences
import javax.inject.Inject

class SaveGender @Inject constructor(private val preferences: Preferences) {
    operator fun invoke(gender: Gender){
        preferences.saveGender(gender)
    }
}