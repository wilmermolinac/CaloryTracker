package com.wamcstudios.calorytracker.tracker.presentation.search.components

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.wamcstudios.calorytracker.R
import com.wamcstudios.calorytracker.core.presentation.components.CaloryDefaultTextField
import com.wamcstudios.calorytracker.tracker.domain.model.TrackableFood
import com.wamcstudios.calorytracker.tracker.presentation.search.TrackableFoodUiState
import com.wamcstudios.calorytracker.tracker.presentation.tracker_overview.components.NutrientInfo
import com.wamcstudios.calorytracker.ui.theme.LocalSpacing

@Composable
fun TrackableFoodItem(
    modifier: Modifier = Modifier,
    trackableFoodUiState: TrackableFoodUiState,
    onAmountChange: (String) -> Unit,
    onTrack: () -> Unit,
    onClick: () -> Unit, focusManager: FocusManager,
) {

    val spacing = LocalSpacing.current

    ElevatedCard(modifier = modifier, onClick = { onClick() }) {

        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .padding(spacing.spaceSmall)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (trackableFoodUiState.food.imageUrl.isNullOrEmpty()) {
                    Image(
                        modifier = Modifier
                            .size(spacing.iconButtonSizeExtra + spacing.spaceMedium)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(spacing.spaceMicroSmall)),
                        painter = painterResource(id = R.drawable.ic_burger),
                        contentDescription = null
                    )
                } else {
                    AsyncImage(
                        modifier = Modifier
                            .size(spacing.iconButtonSizeExtra + spacing.spaceMedium)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(spacing.spaceMicroSmall)),
                        model = trackableFoodUiState.food.imageUrl,
                        contentDescription = trackableFoodUiState.food.name,
                        error = painterResource(id = R.drawable.ic_burger),
                        fallback = painterResource(id = R.drawable.ic_burger)
                    )
                }

                Spacer(modifier = Modifier.width(spacing.spaceSmall))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = trackableFoodUiState.food.name,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )

                    Spacer(modifier = Modifier.height(spacing.spaceNanoSmall))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            modifier = Modifier.weight(1f), text = stringResource(
                                id = R.string.kcal_per_100g,
                                trackableFoodUiState.food.caloriesPer100g,
                                100,
                            ), style = MaterialTheme.typography.titleMedium, maxLines = 2
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(spacing.spaceMicroSmall)
                        ) {

                            NutrientInfo(
                                name = stringResource(id = R.string.carbs),
                                amount = trackableFoodUiState.food.carbsPer100g,
                                unit = stringResource(id = R.string.grams),
                                nameTextStyle = MaterialTheme.typography.titleMedium,
                                amountTextStyle = MaterialTheme.typography.titleMedium
                            )

                            NutrientInfo(
                                name = stringResource(id = R.string.protein),
                                amount = trackableFoodUiState.food.proteinPer100g,
                                unit = stringResource(id = R.string.grams),
                                nameTextStyle = MaterialTheme.typography.titleMedium,
                                amountTextStyle = MaterialTheme.typography.titleMedium
                            )

                            NutrientInfo(
                                name = stringResource(id = R.string.fat),
                                amount = trackableFoodUiState.food.fatPer100g,
                                unit = stringResource(id = R.string.grams),
                                nameTextStyle = MaterialTheme.typography.titleMedium,
                                amountTextStyle = MaterialTheme.typography.titleMedium
                            )

                        }

                    }


                }

            }

            AnimatedVisibility(visible = trackableFoodUiState.isExpanded) {

                Row(
                    modifier = Modifier
                        .padding(spacing.spaceSmall)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    CaloryDefaultTextField(
                        modifier = Modifier.weight(1f),
                        value = trackableFoodUiState.amount,
                        onValueChange = { onAmountChange(it) },
                        unit = stringResource(id = R.string.grams),
                        focusManager = focusManager,
                        valueStyle = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        unitStyle = MaterialTheme.typography.titleMedium,
                        onDone = {
                            onTrack()
                        },
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = false,
                            keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
                        ), isKeyboardActionsDone = true
                    )

                    Button(onClick = { onTrack() }) {
                        Text(text = stringResource(id = R.string.add))
                    }

                }


            }
        }

    }
}

@Preview
@Composable
fun TrackableFoodItemPreview() {
    TrackableFoodItem(
        trackableFoodUiState = TrackableFoodUiState(
            isExpanded = true, food = TrackableFood(
                name = "Pollo",
                imageUrl = null,
                caloriesPer100g = 250,
                carbsPer100g = 50,
                proteinPer100g = 150,
                fatPer100g = 50
            )
        ),
        onClick = {},
        onAmountChange = {},
        onTrack = {},
        focusManager = LocalFocusManager.current
    )
}