package com.wamcstudios.calorytracker.tracker.presentation.search

import com.wamcstudios.calorytracker.tracker.domain.model.TrackableFood

data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded: Boolean = false,
    val amount: String = "100",
)
