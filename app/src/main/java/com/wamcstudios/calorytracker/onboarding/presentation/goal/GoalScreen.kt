package com.wamcstudios.calorytracker.onboarding.presentation.goal

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.wamcstudios.calorytracker.navigation.utils.UiEvent
import com.wamcstudios.calorytracker.onboarding.presentation.goal.components.GoalContent

@Composable
fun GoalScreen(onNavigate: (UiEvent) -> Unit, viewModel: GoalViewModel = hiltViewModel()) {

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

                is UiEvent.ShowSnackBar -> {}

            }
        }

    }

    Scaffold {
        GoalContent(
            modifier = Modifier.padding(it),
            state = state,
            onEvent = { viewModel.onEvent(it) },
            onNavigateUp = { onNavigate(UiEvent.NavigateUp) })
    }


}