package com.wamcstudios.calorytracker.onboarding.presentation.weight.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.wamcstudios.calorytracker.R
import com.wamcstudios.calorytracker.core.presentation.components.CaloryDefaultActionButton
import com.wamcstudios.calorytracker.core.presentation.components.CaloryDefaultTextField
import com.wamcstudios.calorytracker.onboarding.presentation.weight.WeightEvent
import com.wamcstudios.calorytracker.onboarding.presentation.weight.WeightState
import com.wamcstudios.calorytracker.ui.theme.LocalSpacing

@Composable
fun WeightContent(
    modifier: Modifier = Modifier,
    state: WeightState,
    onEvent: (WeightEvent) -> Unit, onNavigateUp: () -> Unit,
) {

    val spacing = LocalSpacing.current
    val focusManager = LocalFocusManager.current

    Box(modifier = modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTapGestures(onTap = {
                focusManager.clearFocus()
            })
        }) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.whats_your_weight),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            CaloryDefaultTextField(
                value = state.weightValue,
                onValueChange = { onEvent(WeightEvent.ChangeValueWeight(it)) },
                unit = stringResource(id = R.string.kg),
                focusManager = focusManager, onDone = { onEvent(WeightEvent.OnClickNext) })

        }

        Row(
            modifier = Modifier
                .padding(spacing.spaceSmall)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CaloryDefaultActionButton(
                onClick = { onNavigateUp() },
                text = stringResource(id = R.string.back), isEnabled = true
            )

            CaloryDefaultActionButton(
                onClick = { onEvent(WeightEvent.OnClickNext) },
                text = stringResource(id = R.string.next), isEnabled = true
            )
        }

    }
}

@Preview
@Composable
fun WeightContentPreview() {
    WeightContent(state = WeightState(), onEvent = {}, onNavigateUp = {})
}