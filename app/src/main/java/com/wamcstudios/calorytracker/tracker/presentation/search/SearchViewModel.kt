package com.wamcstudios.calorytracker.tracker.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.query
import com.wamcstudios.calorytracker.R
import com.wamcstudios.calorytracker.core.domain.use_case.CoreUseCases
import com.wamcstudios.calorytracker.core.utils.UiText
import com.wamcstudios.calorytracker.navigation.utils.UiEvent
import com.wamcstudios.calorytracker.tracker.domain.model.MealType
import com.wamcstudios.calorytracker.tracker.domain.use_case.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val trackedUseCases: TrackerUseCases,
    private val coreUseCases: CoreUseCases, private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var state by mutableStateOf(SearchState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    init {
        getNavigationData()
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnAmountForFoodChange -> {
                state = state.copy(trackableFood = state.trackableFood.map {
                    if (it.food == event.food) {
                        it.copy(amount = coreUseCases.filterOutDigits(event.amount))
                    } else {
                        it
                    }
                })

            }

            is SearchEvent.OnQueryChange -> {
                state = state.copy(query = event.query)
            }

            SearchEvent.OnSearch -> {

                executeSearch()

            }

            is SearchEvent.OnSearchFocusChange -> {

                state = state.copy(isHintVisible = !event.isFocused && state.query.isBlank())


            }

            is SearchEvent.OnToggleTrackableFood -> {
                state = state.copy(trackableFood = state.trackableFood.map {
                    if (it.food == event.food) {
                        it.copy(isExpanded = !it.isExpanded)
                    } else {
                        it
                    }
                })

            }

            is SearchEvent.OnTrackFoodClick -> {
                trackFood(event)

            }
        }
    }

    private fun executeSearch() {
        viewModelScope.launch {
            state = state.copy(isSearching = true)
            if (state.query.isNullOrBlank()) {
                state = state.copy(isSearching = false)
                _uiEvent.send(UiEvent.ShowSnackBar(message = UiText.StringResource(R.string.msg_error_query_empty)))
                return@launch
            }

            trackedUseCases.searchFood(query = state.query, pageSize = 100).onSuccess { foods ->

                state = state.copy(trackableFood = foods.map {
                    TrackableFoodUiState(food = it)
                }, isSearching = false, query = "")

            }.onFailure {

                state = state.copy(isSearching = false)

                val message = it.message?.let {
                    UiText.DynamicString(it)
                } ?: run {
                    UiText.StringResource(R.string.error_something_went_wrong)
                }

                _uiEvent.send(UiEvent.ShowSnackBar(message = message))
            }
        }
    }

    private fun trackFood(event: SearchEvent.OnTrackFoodClick) {

        viewModelScope.launch {
            val uiState = state.trackableFood.find {
                it.food == event.food
            }



            trackedUseCases.trackFood(
                food = uiState?.food ?: return@launch,
                amount = uiState.amount.toIntOrNull() ?: return@launch,
                mealType = MealType.fromString(state.mealTypeName),
                date = LocalDate.of(state.year, state.monthValue, state.dayOfMonth)
            )

            _uiEvent.send(UiEvent.NavigateUp)
        }

    }


    private fun getNavigationData() {

        savedStateHandle.get<String>("mealTypeName")?.let { navData ->
            state = state.copy(mealTypeName = navData)
        }

        savedStateHandle.get<Int>("dayOfMonth")?.let { navData ->
            state = state.copy(dayOfMonth = navData)
        }

        savedStateHandle.get<Int>("monthValue")?.let { navData ->
            state = state.copy(monthValue = navData)
        }

        savedStateHandle.get<Int>("year")?.let { navData ->
            state = state.copy(year = navData)
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}