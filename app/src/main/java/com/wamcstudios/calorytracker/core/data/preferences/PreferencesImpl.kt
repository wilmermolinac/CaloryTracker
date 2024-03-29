package com.wamcstudios.calorytracker.core.data.preferences

import android.content.SharedPreferences
import com.wamcstudios.calorytracker.core.common.PreferencesConstant
import com.wamcstudios.calorytracker.core.domain.model.ActivityLevel
import com.wamcstudios.calorytracker.core.domain.model.Gender
import com.wamcstudios.calorytracker.core.domain.model.GoalType
import com.wamcstudios.calorytracker.core.domain.model.UserInfo
import com.wamcstudios.calorytracker.core.domain.preferences.Preferences
import javax.inject.Inject

class PreferencesImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    Preferences {
    override fun saveAge(age: Int) {
        sharedPreferences.edit().putInt(PreferencesConstant.AGE_PREFERENCES, age).apply()
    }

    override fun saveGender(gender: Gender) {
        sharedPreferences.edit().putString(PreferencesConstant.GENDER_PREFERENCES, gender.name)
            .apply()
    }

    override fun saveWeight(weight: Float) {
        sharedPreferences.edit().putFloat(PreferencesConstant.WEIGHT_PREFERENCES, weight).apply()
    }

    override fun saveHeight(height: Int) {
        sharedPreferences.edit().putInt(PreferencesConstant.HEIGHT_PREFERENCES, height).apply()
    }

    override fun saveActivityLevel(level: ActivityLevel) {
        sharedPreferences.edit()
            .putString(PreferencesConstant.ACTIVITY_LEVEL_PREFERENCES, level.name).apply()
    }

    override fun saveGoalType(type: GoalType) {
        sharedPreferences.edit().putString(PreferencesConstant.GOAL_TYPE_PREFERENCES, type.name)
            .apply()
    }

    override fun saveCarbRatio(ratio: Float) {
        sharedPreferences.edit().putFloat(PreferencesConstant.CARB_RATIO_PREFERENCES, ratio).apply()
    }

    override fun saveProteinRatio(ratio: Float) {
        sharedPreferences.edit().putFloat(PreferencesConstant.PROTEIN_RATIO_PREFERENCES, ratio)
            .apply()
    }

    override fun saveFatRatio(ratio: Float) {
        sharedPreferences.edit().putFloat(PreferencesConstant.FAT_RATIO_PREFERENCES, ratio).apply()
    }

    override fun loadUserInfo(): UserInfo {
        val gender = Gender.fromString(
            sharedPreferences.getString(
                PreferencesConstant.GENDER_PREFERENCES, Gender.Male.name
            ).toString()
        )
        val age = sharedPreferences.getInt(PreferencesConstant.AGE_PREFERENCES, 18)
        val weight = sharedPreferences.getFloat(PreferencesConstant.WEIGHT_PREFERENCES, 60f)
        val height = sharedPreferences.getInt(PreferencesConstant.HEIGHT_PREFERENCES, 170)
        val activityLevel = ActivityLevel.fromString(
            sharedPreferences.getString(
                PreferencesConstant.ACTIVITY_LEVEL_PREFERENCES, ActivityLevel.Medium.name
            ).toString()
        )
        val goalType = GoalType.fromString(
            sharedPreferences.getString(
                PreferencesConstant.GOAL_TYPE_PREFERENCES, GoalType.KeepWeight.name
            ).toString()
        )

        val carbRatio = sharedPreferences.getFloat(PreferencesConstant.CARB_RATIO_PREFERENCES, 0f)
        val proteinRatio =
            sharedPreferences.getFloat(PreferencesConstant.PROTEIN_RATIO_PREFERENCES, 0f)

        val fatRatio = sharedPreferences.getFloat(PreferencesConstant.FAT_RATIO_PREFERENCES, 0f)

        return UserInfo(
            gender = gender,
            age = age,
            weight = weight,
            height = height,
            activityLevel = activityLevel,
            goalType = goalType,
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            fatRatio = fatRatio
        )
    }

    override fun saveShouldShowOnboarding(shouldShow: Boolean) {
        sharedPreferences.edit()
            .putBoolean(PreferencesConstant.SHOULD_ONBOARDING_PREFERENCES, shouldShow).apply()
    }

    override fun loadShouldShowOnboarding(): Boolean {
        return sharedPreferences.getBoolean(PreferencesConstant.SHOULD_ONBOARDING_PREFERENCES, true)
    }
}