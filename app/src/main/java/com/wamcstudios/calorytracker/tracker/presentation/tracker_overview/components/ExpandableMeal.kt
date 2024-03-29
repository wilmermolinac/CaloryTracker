package com.wamcstudios.calorytracker.tracker.presentation.tracker_overview.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wamcstudios.calorytracker.R
import com.wamcstudios.calorytracker.core.utils.UiText
import com.wamcstudios.calorytracker.tracker.domain.model.Meal
import com.wamcstudios.calorytracker.tracker.domain.model.MealType
import com.wamcstudios.calorytracker.ui.theme.LocalSpacing

@Composable
fun ExpandableMeal(
    modifier: Modifier = Modifier,
    meal: Meal,
    onToggleClick: () -> Unit,
    content: @Composable () -> Unit,
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current

    Column(modifier = modifier) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggleClick() }
            .padding(spacing.spaceMediumSmall), verticalAlignment = Alignment.CenterVertically) {

            Image(
                modifier = Modifier.size(spacing.iconButtonSizeExtra),
                painter = painterResource(id = meal.drawableRes),
                contentDescription = meal.name.asString(context = context)
            )

            Spacer(modifier = Modifier.width(spacing.spaceSmall))

            Column(modifier = Modifier.weight(1f)) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = meal.name.asString(context),
                        style = MaterialTheme.typography.titleLarge
                    )

                    val icon = if (meal.isExpanded) {
                        Icons.Default.KeyboardArrowUp
                    } else {
                        Icons.Default.KeyboardArrowDown
                    }
                    val iconDescription = if (meal.isExpanded) {
                        stringResource(id = R.string.collapse)
                    } else {
                        stringResource(id = R.string.extend)
                    }
                    Icon(
                        modifier = Modifier.size(spacing.iconSize),
                        imageVector = icon,
                        contentDescription = iconDescription
                    )

                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    UnitDisplay(
                        amount = meal.calories,
                        unit = stringResource(id = R.string.kcal),
                        amountStyle = MaterialTheme.typography.headlineMedium,
                        unitTextStyle = MaterialTheme.typography.titleSmall
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(spacing.spaceMicroSmall)
                    ) {

                        NutrientInfo(
                            name = stringResource(id = R.string.carbs),
                            amount = meal.carbs,
                            unit = stringResource(id = R.string.grams)
                        )

                        NutrientInfo(
                            name = stringResource(id = R.string.protein),
                            amount = meal.protein,
                            unit = stringResource(id = R.string.grams)
                        )

                        NutrientInfo(
                            name = stringResource(id = R.string.fat),
                            amount = meal.fat,
                            unit = stringResource(id = R.string.grams)
                        )

                    }

                }


            }


        }

        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        AnimatedVisibility(visible = meal.isExpanded) {

            content()
        }
    }

}

@Preview
@Composable
fun ExpandableMealPreview() {
    ExpandableMeal(meal = Meal(
        fat = 279, protein = 314,
        carbs = 90,
        calories = 2577,
        name = UiText.StringResource(R.string.breakfast),
        drawableRes = R.drawable.ic_breakfast,
        mealType = MealType.Breakfast, isExpanded = true
    ), onToggleClick = {}, content = {})
}