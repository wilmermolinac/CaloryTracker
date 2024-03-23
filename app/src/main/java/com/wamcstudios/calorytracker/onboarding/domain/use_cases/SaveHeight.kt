package com.wamcstudios.calorytracker.onboarding.domain.use_cases

import android.content.SharedPreferences
import com.wamcstudios.calorytracker.core.domain.preferences.Preferences
import javax.inject.Inject

class SaveHeight @Inject constructor(private val preferences: Preferences) {

    operator fun invoke(height: Int) {
        preferences.saveHeight(height)
    }
}