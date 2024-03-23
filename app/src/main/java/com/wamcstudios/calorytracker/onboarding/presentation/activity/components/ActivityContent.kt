package com.wamcstudios.calorytracker.onboarding.presentation.activity.components

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
import com.wamcstudios.calorytracker.core.domain.model.ActivityLevel
import com.wamcstudios.calorytracker.core.presentation.components.CaloryDefaultActionButton
import com.wamcstudios.calorytracker.core.presentation.components.CaloryDefaultSelectButton
import com.wamcstudios.calorytracker.onboarding.presentation.activity.ActivityEvent
import com.wamcstudios.calorytracker.onboarding.presentation.activity.ActivityState
import com.wamcstudios.calorytracker.ui.theme.LocalSpacing

@Composable
fun ActivityContent(
    modifier: Modifier = Modifier,
    state: ActivityState,
    onEvent: (ActivityEvent) -> Unit,
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
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.whats_your_activity_level),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(spacing.spaceMedium)
            ) {

                CaloryDefaultSelectButton(
                    text = ActivityLevel.Low.name,
                    isSelected = ActivityLevel.Low == state.activityLevelSelected,
                    onClick = { onEvent(ActivityEvent.ChangeValueActivityLevelSelected(ActivityLevel.Low)) })

                CaloryDefaultSelectButton(
                    text = ActivityLevel.Medium.name,
                    isSelected = ActivityLevel.Medium == state.activityLevelSelected,
                    onClick = { onEvent(ActivityEvent.ChangeValueActivityLevelSelected(ActivityLevel.Medium)) })

                CaloryDefaultSelectButton(
                    text = ActivityLevel.High.name,
                    isSelected = ActivityLevel.High == state.activityLevelSelected,
                    onClick = { onEvent(ActivityEvent.ChangeValueActivityLevelSelected(ActivityLevel.High)) })

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
                onClick = { onEvent(ActivityEvent.OnNextClick) },
                text = stringResource(id = R.string.next), isEnabled = true
            )

        }

    }
}

@Preview
@Composable
fun ActivityContentPreview() {
    ActivityContent(state = ActivityState(), onEvent = {}, onNavigateUp = {})
}