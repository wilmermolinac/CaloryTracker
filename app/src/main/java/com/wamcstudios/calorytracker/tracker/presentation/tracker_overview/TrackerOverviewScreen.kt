package com.wamcstudios.calorytracker.tracker.presentation.tracker_overview

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.wamcstudios.calorytracker.navigation.utils.UiEvent
import com.wamcstudios.calorytracker.tracker.presentation.tracker_overview.components.TrackerOverviewContent

@Composable
fun TrackerOverviewScreen(
    onNavigate: (UiEvent) -> Unit,
    viewModel: TrackerOverviewViewModel = hiltViewModel(),
) {

    val state = viewModel.state

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collect() { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    onNavigate(event)
                }

                UiEvent.NavigateUp -> {
                    onNavigate(event)
                }

                is UiEvent.PreviousBackStackEntry -> {
                    onNavigate(event)
                }

                is UiEvent.ShowSnackBar -> {

                }
            }
        }

    }

    Scaffold {
        TrackerOverviewContent(
            modifier = Modifier.padding(it),
            state = state,
            onEvent = { viewModel.onEvent(it) })
    }
}