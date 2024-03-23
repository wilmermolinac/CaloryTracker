package com.wamcstudios.calorytracker.onboarding.presentation.gender

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.wamcstudios.calorytracker.R
import com.wamcstudios.calorytracker.core.presentation.components.CaloryDefaultActionButton
import com.wamcstudios.calorytracker.navigation.utils.UiEvent
import com.wamcstudios.calorytracker.onboarding.presentation.gender.components.GenderContent
import com.wamcstudios.calorytracker.ui.theme.LocalSpacing

@Composable
fun GenderScreen(onNavigate: (UiEvent) -> Unit, viewModel: GenderViewModel = hiltViewModel()) {

    val state = viewModel.state
    val spacing = LocalSpacing.current

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

                }
            }
        }

    }

    Scaffold(bottomBar = {
        Row(
            modifier = Modifier
                .padding(spacing.spaceSmall)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            CaloryDefaultActionButton(
                onClick = { viewModel.onEvent(GenderEvent.OnCLickNext) },
                text = stringResource(id = R.string.next), isEnabled = true
            )
        }

    }) {
        GenderContent(
            modifier = Modifier.padding(it),
            state = state,
            onEvent = { viewModel.onEvent(it) })
    }
}