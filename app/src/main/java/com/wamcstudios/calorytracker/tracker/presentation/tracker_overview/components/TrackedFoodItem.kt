package com.wamcstudios.calorytracker.tracker.presentation.tracker_overview.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.wamcstudios.calorytracker.R
import com.wamcstudios.calorytracker.tracker.domain.model.MealType
import com.wamcstudios.calorytracker.tracker.domain.model.TrackedFood
import com.wamcstudios.calorytracker.ui.theme.LocalSpacing
import java.time.LocalDate

@Composable
fun TrackedFoodItem(
    modifier: Modifier = Modifier,
    trackedFood: TrackedFood,
    onDeleteClick: () -> Unit,
) {

    val spacing = LocalSpacing.current

    ElevatedCard(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .padding(spacing.spaceSmall)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {


            if (!trackedFood.imageUrl.isNullOrEmpty()) {
                AsyncImage(
                    modifier = Modifier
                        .size(spacing.iconButtonSizeExtra + spacing.spaceMedium)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(spacing.spaceMicroSmall)),
                    model = trackedFood.imageUrl,
                    contentDescription = trackedFood.name,
                    contentScale = ContentScale.Crop,
                    error = painterResource(id = R.drawable.ic_burger),
                    fallback = painterResource(id = R.drawable.ic_burger)
                )
            } else {
                Image(
                    modifier = Modifier.size(spacing.iconButtonSizeExtra + spacing.spaceMedium),
                    painter = painterResource(id = R.drawable.ic_burger),
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.width(spacing.spaceSmall))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        modifier = Modifier.weight(1f),
                        text = trackedFood.name,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                        overflow = TextOverflow.Ellipsis, maxLines = 2
                    )


                    IconButton(onClick = { onDeleteClick() }) {
                        Icon(
                            modifier = Modifier.size(spacing.iconSize),
                            imageVector = Icons.Default.Clear,
                            contentDescription = stringResource(id = R.string.delete),
                            tint = MaterialTheme.colorScheme.error
                        )
                    }


                }

                Spacer(modifier = Modifier.height(spacing.spaceNanoSmall))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(
                            id = R.string.nutrient_info,
                            trackedFood.amount,
                            trackedFood.calories,
                        ), style = MaterialTheme.typography.titleMedium, maxLines = 2
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(spacing.spaceMicroSmall)
                    ) {

                        NutrientInfo(
                            name = stringResource(id = R.string.carbs),
                            amount = trackedFood.carbs,
                            unit = stringResource(id = R.string.grams),
                            nameTextStyle = MaterialTheme.typography.titleMedium,
                            amountTextStyle = MaterialTheme.typography.titleMedium
                        )

                        NutrientInfo(
                            name = stringResource(id = R.string.protein),
                            amount = trackedFood.protein,
                            unit = stringResource(id = R.string.grams),
                            nameTextStyle = MaterialTheme.typography.titleMedium,
                            amountTextStyle = MaterialTheme.typography.titleMedium
                        )

                        NutrientInfo(
                            name = stringResource(id = R.string.fat),
                            amount = trackedFood.fat,
                            unit = stringResource(id = R.string.grams),
                            nameTextStyle = MaterialTheme.typography.titleMedium,
                            amountTextStyle = MaterialTheme.typography.titleMedium
                        )

                    }

                }
            }


        }
    }


}

@Preview
@Composable
fun TrackedFoodItemPreview() {
    TrackedFoodItem(trackedFood = TrackedFood(
        id = 1993,
        name = "Pollo Pollo Pollo Pollo Pollo Pollo",
        carbs = 50,
        protein = 150,
        fat = 50,
        imageUrl = null,
        mealType = MealType.Snack,
        amount = 1,
        date = LocalDate.now(),
        calories = 500
    ), onDeleteClick = {})
}