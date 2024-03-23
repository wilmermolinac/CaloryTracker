package com.wamcstudios.calorytracker.onboarding.presentation.height

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.wamcstudios.calorytracker.navigation.utils.UiEvent
import com.wamcstudios.calorytracker.onboarding.presentation.height.components.HeightContent


@Composable
fun HeightScreen(onNavigate: (UiEvent) -> Unit, viewModel: HeightViewModel = hiltViewModel()) {

    val state = viewModel.state
    val context = LocalContext.current

    var snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(key1 = true) {
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
                    snackbarHostState.showSnackbar(message = event.message.asString(context))
                }
            }
        }
    }


    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) {
        HeightContent(
            modifier = Modifier.padding(it),
            state = state,
            onEvent = { viewModel.onEvent(it) },
            onNavigateUp = { onNavigate(UiEvent.NavigateUp) })
    }
}