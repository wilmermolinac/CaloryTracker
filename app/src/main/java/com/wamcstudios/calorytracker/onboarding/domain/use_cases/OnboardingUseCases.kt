package com.wamcstudios.calorytracker.onboarding.domain.use_cases

data class OnboardingUseCases(
    val saveGender: SaveGender,
    val saveAge: SaveAge,
    val validateAge: ValidateAge,
    val saveHeight: SaveHeight,
    val saveWeight: SaveWeight,
    val saveActivityLevel: SaveActivityLevel,
    val saveGoalType: SaveGoalType,
    val saveCarbsRatio: SaveCarbsRatio,
    val saveProteinsRatio: SaveProteinsRatio,
    val saveFatsRatio: SaveFatsRatio,
)
