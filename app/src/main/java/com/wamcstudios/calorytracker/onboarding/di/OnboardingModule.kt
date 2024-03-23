package com.wamcstudios.calorytracker.onboarding.di

import com.wamcstudios.calorytracker.core.domain.preferences.Preferences
import com.wamcstudios.calorytracker.onboarding.domain.use_cases.OnboardingUseCases
import com.wamcstudios.calorytracker.onboarding.domain.use_cases.SaveActivityLevel
import com.wamcstudios.calorytracker.onboarding.domain.use_cases.SaveAge
import com.wamcstudios.calorytracker.onboarding.domain.use_cases.SaveCarbsRatio
import com.wamcstudios.calorytracker.onboarding.domain.use_cases.SaveFatsRatio
import com.wamcstudios.calorytracker.onboarding.domain.use_cases.SaveGender
import com.wamcstudios.calorytracker.onboarding.domain.use_cases.SaveGoalType
import com.wamcstudios.calorytracker.onboarding.domain.use_cases.SaveHeight
import com.wamcstudios.calorytracker.onboarding.domain.use_cases.SaveProteinsRatio
import com.wamcstudios.calorytracker.onboarding.domain.use_cases.SaveWeight
import com.wamcstudios.calorytracker.onboarding.domain.use_cases.ValidateAge
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OnboardingModule {

    @Provides
    @Singleton
    fun providesOnboardingUseCases(preferences: Preferences): OnboardingUseCases {
        return OnboardingUseCases(
            saveGender = SaveGender(preferences),
            saveAge = SaveAge(preferences),
            validateAge = ValidateAge(),
            saveHeight = SaveHeight(preferences),
            saveWeight = SaveWeight(preferences),
            saveActivityLevel = SaveActivityLevel(preferences),
            saveGoalType = SaveGoalType(preferences),
            saveCarbsRatio = SaveCarbsRatio(preferences),
            saveProteinsRatio = SaveProteinsRatio(preferences),
            saveFatsRatio = SaveFatsRatio(preferences)
        )
    }
}