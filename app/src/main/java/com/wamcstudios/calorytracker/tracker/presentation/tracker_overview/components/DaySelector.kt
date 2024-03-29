package com.wamcstudios.calorytracker.tracker.presentation.tracker_overview.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.wamcstudios.calorytracker.R
import com.wamcstudios.calorytracker.core.utils.parseDateText
import com.wamcstudios.calorytracker.tracker.presentation.tracker_overview.TrackerOverviewEvent
import com.wamcstudios.calorytracker.ui.theme.LocalSpacing
import java.time.LocalDate

@Composable
fun DaySelector(
    modifier: Modifier = Modifier,
    date: LocalDate,
    onEvent: (TrackerOverviewEvent) -> Unit,
) {

    val spacing = LocalSpacing.current

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        IconButton(onClick = { onEvent(TrackerOverviewEvent.OnPreviousDayClick) }) {

            Icon(
                modifier = Modifier.size(spacing.iconSize),
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.previous_day)
            )
        }

        val dateText = parseDateText(date = date)
        Text(
            text = dateText,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )

        IconButton(onClick = { onEvent(TrackerOverviewEvent.OnNextDayClick) }) {

            Icon(
                modifier = Modifier.size(spacing.iconSize),
                imageVector = Icons.AutoMirrored.Default.ArrowForward,
                contentDescription = stringResource(id = R.string.next_day)
            )
        }
    }
}