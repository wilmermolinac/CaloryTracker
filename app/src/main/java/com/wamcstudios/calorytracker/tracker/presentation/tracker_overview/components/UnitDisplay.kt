package com.wamcstudios.calorytracker.tracker.presentation.tracker_overview.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.wamcstudios.calorytracker.ui.theme.LocalSpacing

@Composable
fun UnitDisplay(
    modifier: Modifier = Modifier,
    amount: Int,
    unit: String,
    amountStyle: TextStyle = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.onBackground),
    unitTextStyle: TextStyle = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onBackground),
) {

    val spacing = LocalSpacing.current

    Row(modifier = modifier) {
        Text(
            modifier = Modifier.alignBy(LastBaseline),
            text = amount.toString(),
            style = amountStyle
        )

        Spacer(modifier = Modifier.width(spacing.spaceNanoSmall))

        Text(modifier = modifier.alignByBaseline(), text = unit, style = unitTextStyle)
    }


}

@Preview
@Composable
fun UnitDisplayPreview() {
    UnitDisplay(amount = 10, unit = "gr")
}