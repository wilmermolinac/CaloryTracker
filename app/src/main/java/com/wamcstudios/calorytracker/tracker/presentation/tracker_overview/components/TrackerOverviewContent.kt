package com.wamcstudios.calorytracker.tracker.presentation.tracker_overview.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wamcstudios.calorytracker.R
import com.wamcstudios.calorytracker.tracker.domain.model.Meal
import com.wamcstudios.calorytracker.tracker.domain.model.TrackedFood
import com.wamcstudios.calorytracker.tracker.presentation.tracker_overview.TrackerOverviewEvent
import com.wamcstudios.calorytracker.tracker.presentation.tracker_overview.TrackerOverviewState
import com.wamcstudios.calorytracker.ui.theme.LocalSpacing

@Composable
fun TrackerOverviewContent(
    modifier: Modifier = Modifier,
    state: TrackerOverviewState,
    onEvent: (TrackerOverviewEvent) -> Unit,
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current

    Box(modifier = modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
        ) {
            item {
                NutrientHeader(modifier = Modifier.fillMaxWidth(), state = state)
            }

            item {
                DaySelector(
                    modifier = Modifier.fillMaxWidth(),
                    onEvent = onEvent,
                    date = state.date
                )

            }

            items(state.meals) { item: Meal ->

                ExpandableMeal(meal = item, onToggleClick = {
                    onEvent(TrackerOverviewEvent.OnToggleMealClick(item))
                }) {

                    Column(
                        modifier = Modifier
                            .padding(
                                start = spacing.spaceSmall,
                                end = spacing.spaceSmall,
                                bottom = spacing.spaceSmall
                            )
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(spacing.spaceSmall)
                    ) {

                        state.trackedFoods.filter {
                            it.mealType == item.mealType
                        }.forEach { item: TrackedFood ->

                            TrackedFoodItem(
                                trackedFood = item,
                                onDeleteClick = {
                                    onEvent(
                                        TrackerOverviewEvent.OnDeleteTrackedFoodClick(item)
                                    )
                                })
                        }


                        AddButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(
                                id = R.string.add_meal,
                                item.name.asString(context)
                            ),
                            onClick = { onEvent(TrackerOverviewEvent.OnAddFoodClick(item)) })
                    }
                }

            }
        }

    }

}

@Preview
@Composable
fun TrackerOverviewContentPreview() {
    TrackerOverviewContent(state = TrackerOverviewState(), onEvent = {})
}