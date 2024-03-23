package com.wamcstudios.calorytracker.onboarding.presentation.gender.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.wamcstudios.calorytracker.R
import com.wamcstudios.calorytracker.core.domain.model.Gender
import com.wamcstudios.calorytracker.core.presentation.components.CaloryDefaultSelectButton
import com.wamcstudios.calorytracker.onboarding.presentation.gender.GenderEvent
import com.wamcstudios.calorytracker.onboarding.presentation.gender.GenderState
import com.wamcstudios.calorytracker.ui.theme.LocalSpacing

@Composable
fun GenderContent(
    modifier: Modifier = Modifier,
    state: GenderState,
    onEvent: (GenderEvent) -> Unit,
) {

    val spacing = LocalSpacing.current

    Box(modifier = modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.whats_your_gender),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            Row(verticalAlignment = Alignment.CenterVertically) {
                CaloryDefaultSelectButton(
                    text = Gender.Male.name,
                    isSelected = state.genderSelected == Gender.Male,
                    onClick = { onEvent(GenderEvent.ChangeGenderSelected(gender = Gender.Male)) })

                Spacer(modifier = Modifier.width(spacing.spaceMicroSmall))

                CaloryDefaultSelectButton(
                    text = Gender.Female.name,
                    isSelected = state.genderSelected == Gender.Female,
                    onClick = { onEvent(GenderEvent.ChangeGenderSelected(gender = Gender.Female)) })

            }

        }

    }
}

@Preview
@Composable
fun GenderContentPreview() {
    GenderContent(state = GenderState(), onEvent = {})
}