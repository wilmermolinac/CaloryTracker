package com.wamcstudios.calorytracker.onboarding.domain.use_cases

import com.wamcstudios.calorytracker.core.domain.preferences.Preferences
import javax.inject.Inject

class SaveProteinsRatio @Inject constructor(private val preferences: Preferences) {

    operator fun invoke(proteins: Float) {
        preferences.saveProteinRatio(proteins)
    }
}