package com.wamcstudios.calorytracker.onboarding.presentation.nutrient.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.wamcstudios.calorytracker.R
import com.wamcstudios.calorytracker.core.presentation.components.CaloryDefaultActionButton
import com.wamcstudios.calorytracker.core.presentation.components.CaloryDefaultTextField
import com.wamcstudios.calorytracker.onboarding.presentation.nutrient.NutrientEvent
import com.wamcstudios.calorytracker.onboarding.presentation.nutrient.NutrientState
import com.wamcstudios.calorytracker.ui.theme.LocalSpacing

@Composable
fun NutrientContent(
    modifier: Modifier = Modifier,
    state: NutrientState,
    onEvent: (NutrientEvent) -> Unit,
    onNavigateUp: () -> Unit,
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
                modifier = Modifier
                    .padding(horizontal = spacing.spaceMedium)
                    .fillMaxWidth(),
                text = stringResource(id = R.string.what_are_your_nutrient_goals),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(spacing.spaceMedium))

            CaloryDefaultTextField(
                value = state.carbsValue,
                onValueChange = { onEvent(NutrientEvent.ChangeValueCarbs(it)) },
                unit = stringResource(id = R.string.percent_carbs),
                focusManager = focusManager,
                onDone = {},
                isKeyboardActionsDone = false,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.padding(spacing.spaceMedium))

            CaloryDefaultTextField(
                value = state.proteinsValue,
                onValueChange = { onEvent(NutrientEvent.ChangeValueProteins(it)) },
                unit = stringResource(id = R.string.percent_proteins),
                focusManager = focusManager,
                onDone = {},
                isKeyboardActionsDone = false,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.padding(spacing.spaceMedium))

            CaloryDefaultTextField(
                value = state.fatsValue,
                onValueChange = { onEvent(NutrientEvent.ChangeValueFats(it)) },
                unit = stringResource(id = R.string.percent_fats),
                focusManager = focusManager,
                onDone = { onEvent(NutrientEvent.OnClickNext) },
            )


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
                text = stringResource(id = R.string.back),
                isEnabled = true
            )

            CaloryDefaultActionButton(
                onClick = { onEvent(NutrientEvent.OnClickNext) },
                text = stringResource(id = R.string.next),
                isEnabled = true
            )


        }

    }
}

@Preview
@Composable
fun NutrientContentPreview() {
    NutrientContent(state = NutrientState(), onEvent = {}, onNavigateUp = {})
}