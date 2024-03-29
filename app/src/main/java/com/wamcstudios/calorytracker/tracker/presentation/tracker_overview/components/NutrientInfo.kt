package com.wamcstudios.calorytracker.tracker.presentation.tracker_overview.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.wamcstudios.calorytracker.ui.theme.LocalSpacing

@Composable
fun NutrientInfo(
    modifier: Modifier = Modifier, name: String,
    amount: Int,
    unit: String,
    amountTextStyle: TextStyle = MaterialTheme.typography.titleLarge,
    unitTextStyle: TextStyle = MaterialTheme.typography.titleSmall,
    nameTextStyle: TextStyle = MaterialTheme.typography.titleMedium,
) {

    val spacing = LocalSpacing.current

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        UnitDisplay(
            amount = amount,
            unit = unit,
            amountStyle = amountTextStyle,
            unitTextStyle = unitTextStyle
        )
        Spacer(modifier = Modifier.height(spacing.spaceNanoSmall))

        Text(text = name, style = nameTextStyle)

    }

}

@Preview
@Composable
fun NutrientInfoPreview() {
    NutrientInfo(name = "Carbs", amount = 150, unit = "g")
}