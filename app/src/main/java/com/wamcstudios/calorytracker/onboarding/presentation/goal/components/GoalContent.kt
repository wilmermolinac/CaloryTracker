package com.wamcstudios.calorytracker.onboarding.presentation.goal.components

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.wamcstudios.calorytracker.R
import com.wamcstudios.calorytracker.core.domain.model.GoalType
import com.wamcstudios.calorytracker.core.presentation.components.CaloryDefaultActionButton
import com.wamcstudios.calorytracker.core.presentation.components.CaloryDefaultSelectButton
import com.wamcstudios.calorytracker.onboarding.presentation.goal.GoalEvent
import com.wamcstudios.calorytracker.onboarding.presentation.goal.GoalState
import com.wamcstudios.calorytracker.ui.theme.LocalSpacing

@Composable
fun GoalContent(
    modifier: Modifier = Modifier,
    state: GoalState,
    onEvent: (GoalEvent) -> Unit,
    onNavigateUp: () -> Unit,
) {

    val spacing = LocalSpacing.current
    Box(modifier = modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceMedium),
                text = stringResource(id = R.string.lose_keep_or_gain_weight),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium)
            ) {
                CaloryDefaultSelectButton(text = stringResource(id = R.string.lose),
                    isSelected = GoalType.LoseWeight == state.goalTypeSelected,
                    onClick = { onEvent(GoalEvent.ChangeGoalTypeSelected(GoalType.LoseWeight)) })

                CaloryDefaultSelectButton(text = stringResource(id = R.string.keep),
                    isSelected = GoalType.KeepWeight == state.goalTypeSelected,
                    onClick = { onEvent(GoalEvent.ChangeGoalTypeSelected(GoalType.KeepWeight)) })

                CaloryDefaultSelectButton(text = stringResource(id = R.string.gain),
                    isSelected = GoalType.GainWeight == state.goalTypeSelected,
                    onClick = { onEvent(GoalEvent.ChangeGoalTypeSelected(GoalType.GainWeight)) })

            }


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
                onClick = { onEvent(GoalEvent.OnNextClick) },
                text = stringResource(id = R.string.back), isEnabled = true
            )

        }

    }
}

@Preview
@Composable
fun GoalContentPreview() {
    GoalContent(state = GoalState(), onEvent = {}, onNavigateUp = {})
}