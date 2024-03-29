package com.wamcstudios.calorytracker.tracker.presentation.search.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.wamcstudios.calorytracker.R
import com.wamcstudios.calorytracker.core.presentation.components.CaloryDefaultLoading
import com.wamcstudios.calorytracker.tracker.presentation.search.SearchEvent
import com.wamcstudios.calorytracker.tracker.presentation.search.SearchState
import com.wamcstudios.calorytracker.tracker.presentation.search.TrackableFoodUiState
import com.wamcstudios.calorytracker.ui.theme.LocalSpacing

@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    state: SearchState,
    onEvent: (SearchEvent) -> Unit,
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    Box(modifier = modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTapGestures(onTap = {
                focusManager.clearFocus()
            })
        }) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = spacing.spaceMediumExtra,
                start = spacing.spaceMedium,
                end = spacing.spaceMedium,
                bottom = spacing.spaceMediumExtra
            ), verticalArrangement = Arrangement.spacedBy(space = spacing.spaceSmall)
        ) {

            item {
                SearchTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = state.query,
                    onValueChange = { onEvent(SearchEvent.OnQueryChange(it)) },
                    onSearch = { onEvent(SearchEvent.OnSearch) },
                    onFocusChange = {},
                    focusManager = focusManager
                )
            }

            items(state.trackableFood) { item: TrackableFoodUiState ->

                TrackableFoodItem(
                    trackableFoodUiState = item,
                    onAmountChange = {
                        onEvent(
                            SearchEvent.OnAmountForFoodChange(
                                food = item.food,
                                amount = it
                            )
                        )
                    },
                    onTrack = {
                        focusManager.clearFocus()
                        onEvent(SearchEvent.OnTrackFoodClick(item.food))

                    },
                    onClick = {
                        focusManager.clearFocus()
                        onEvent(SearchEvent.OnToggleTrackableFood(item.food))

                    },
                    focusManager = focusManager
                )
            }
        }

        when {
            state.isSearching -> {
                CaloryDefaultLoading(isLoading = state.isSearching)
            }

            state.trackableFood.isEmpty() -> Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = R.string.no_results),
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            )
        }


    }

}