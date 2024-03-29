package com.wamcstudios.calorytracker.tracker.presentation.tracker_overview.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wamcstudios.calorytracker.R
import com.wamcstudios.calorytracker.tracker.presentation.tracker_overview.TrackerOverviewState
import com.wamcstudios.calorytracker.ui.theme.CarbColor
import com.wamcstudios.calorytracker.ui.theme.FatColor
import com.wamcstudios.calorytracker.ui.theme.LocalSpacing
import com.wamcstudios.calorytracker.ui.theme.ProteinColor

@Composable
fun NutrientHeader(modifier: Modifier = Modifier, state: TrackerOverviewState) {

    val spacing = LocalSpacing.current

    val animatedCalorieCount = animateIntAsState(targetValue = state.totalCalories)

    Surface(
        modifier = modifier.fillMaxWidth(), shape = RoundedCornerShape(
            bottomStart = spacing.spaceLargeExtra, bottomEnd = spacing.spaceLargeExtra
        ), color = MaterialTheme.colorScheme.primary, shadowElevation = 2.dp, tonalElevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = spacing.spaceLargeMedium,
                    start = spacing.spaceMedium,
                    end = spacing.spaceMedium,
                    bottom = spacing.spaceLargeMedium
                )
                .fillMaxWidth(),
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {

                UnitDisplay(
                    modifier = Modifier.align(Alignment.Bottom),
                    amount = animatedCalorieCount.value,
                    unit = stringResource(id = R.string.kcal),
                    amountStyle = MaterialTheme.typography.displayMedium.copy(color = MaterialTheme.colorScheme.onPrimary),
                    unitTextStyle = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onPrimary)
                )

                Column(modifier = Modifier.align(Alignment.Bottom)) {
                    Text(
                        text = stringResource(id = R.string.your_goal),
                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onPrimary)
                    )
                    UnitDisplay(
                        amount = state.caloriesGoal,
                        unit = stringResource(id = R.string.kcal),
                        amountStyle = MaterialTheme.typography.displayMedium.copy(color = MaterialTheme.colorScheme.onPrimary),
                        unitTextStyle = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onPrimary)
                    )


                }

            }


            Spacer(modifier = Modifier.height(spacing.spaceSmall))

            NutrientsBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(spacing.spaceLargeSmall),
                carbs = state.totalCarbs,
                protein = state.totalProtein,
                fat = state.totalFat,
                calories = state.totalCalories,
                caloriesGoal = state.caloriesGoal
            )


            Spacer(modifier = Modifier.height(spacing.spaceMediumSmall))


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                NutrientBarInfo(
                    modifier = Modifier.size(spacing.iconButtonSizeExtra + spacing.iconButtonSizeSmall),
                    value = state.totalCarbs,
                    goal = state.carbsGoal,
                    name = stringResource(
                        id = R.string.carbs
                    ),
                    color = CarbColor,
                )

                NutrientBarInfo(
                    modifier = Modifier.size(spacing.iconButtonSizeExtra + spacing.iconButtonSizeSmall),
                    value = state.totalProtein,
                    goal = state.proteinGoal,
                    name = stringResource(
                        id = R.string.protein
                    ),
                    color = ProteinColor,
                )


                NutrientBarInfo(
                    modifier = Modifier.size(spacing.iconButtonSizeExtra + spacing.iconButtonSizeSmall),
                    value = state.totalFat,
                    goal = state.fatGoal,
                    name = stringResource(
                        id = R.string.fat
                    ),
                    color = FatColor,
                )

            }


        }
    }


}

@Preview
@Composable
fun NutrientHeaderPreview() {
    NutrientHeader(
        state = TrackerOverviewState(
            totalCalories = 2450,
            caloriesGoal = 2500,
            totalCarbs = 200,
            totalFat = 50,
            totalProtein = 100, carbsGoal = 250, proteinGoal = 120, fatGoal = 60
        )
    )
}