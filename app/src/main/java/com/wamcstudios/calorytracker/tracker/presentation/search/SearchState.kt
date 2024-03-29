package com.wamcstudios.calorytracker.tracker.presentation.search

import com.wamcstudios.calorytracker.tracker.domain.model.MealType

data class SearchState(
    val query: String = "",
    val isHintVisible: Boolean = false,
    val isSearching: Boolean = false,
    val trackableFood: List<TrackableFoodUiState> = emptyList(),
    val mealTypeName: String = MealType.Breakfast.name,
    val dayOfMonth: Int = 0,
    val monthValue: Int = 0,
    val year: Int = 0,
)
