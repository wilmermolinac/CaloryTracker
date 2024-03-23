package com.wamcstudios.calorytracker.onboarding.presentation.activity

import com.wamcstudios.calorytracker.core.domain.model.ActivityLevel

data class ActivityState(val activityLevelSelected: ActivityLevel = ActivityLevel.Medium)
