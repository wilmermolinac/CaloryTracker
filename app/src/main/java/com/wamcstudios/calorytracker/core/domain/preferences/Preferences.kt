package com.wamcstudios.calorytracker.core.domain.preferences

import com.wamcstudios.calorytracker.core.domain.model.ActivityLevel
import com.wamcstudios.calorytracker.core.domain.model.Gender
import com.wamcstudios.calorytracker.core.domain.model.GoalType
import com.wamcstudios.calorytracker.core.domain.model.UserInfo

interface Preferences {
    fun saveAge(age: Int)
    fun saveGender(gender: Gender)
    fun saveWeight(weight: Float)
    fun saveHeight(height: Int)
    fun saveActivityLevel(level: ActivityLevel)
    fun saveGoalType(type: GoalType)
    fun saveCarbRatio(ratio: Float)
    fun saveProteinRatio(ratio: Float)
    fun saveFatRatio(ratio: Float)
    fun loadUserInfo(): UserInfo
}