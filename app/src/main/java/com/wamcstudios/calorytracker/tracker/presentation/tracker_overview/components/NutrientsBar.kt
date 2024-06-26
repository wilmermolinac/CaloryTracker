package com.wamcstudios.calorytracker.tracker.presentation.tracker_overview.components

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import com.wamcstudios.calorytracker.ui.theme.CarbColor
import com.wamcstudios.calorytracker.ui.theme.FatColor
import com.wamcstudios.calorytracker.ui.theme.ProteinColor

@Composable
fun NutrientsBar(
    modifier: Modifier = Modifier,
    carbs: Int,
    protein: Int,
    fat: Int,
    calories: Int,
    caloriesGoal: Int,
) {

    val background = MaterialTheme.colorScheme.background
    val caloriesExceedColor = MaterialTheme.colorScheme.error

    val carbWidthRatio = remember {
        Animatable(0f)
    }

    val proteinWidthRatio = remember {
        Animatable(0f)
    }

    val fatWidthRatio = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = carbs) {

        carbWidthRatio.animateTo(targetValue = ((carbs * 4f) / caloriesGoal))

    }

    LaunchedEffect(key1 = protein) {

        proteinWidthRatio.animateTo(targetValue = ((protein * 4f) / caloriesGoal))

    }

    LaunchedEffect(key1 = fat) {

        fatWidthRatio.animateTo(targetValue = ((fat * 9f) / caloriesGoal))

    }

    Canvas(modifier = modifier) {

        if (calories <= caloriesGoal) {
            val carbsWidth = carbWidthRatio.value * size.width
            val proteinWidth = proteinWidthRatio.value * size.width
            val fatWidth = fatWidthRatio.value * size.width

            drawRoundRect(color = background, size = size, cornerRadius = CornerRadius(100f))
            drawRoundRect(
                color = FatColor,
                size = Size(width = carbsWidth + proteinWidth + fatWidth, height = size.height),
                cornerRadius = CornerRadius(100f)
            )

            drawRoundRect(
                color = ProteinColor,
                size = Size(width = carbsWidth + proteinWidth, height = size.height),
                cornerRadius = CornerRadius(100f)
            )


            drawRoundRect(
                color = CarbColor,
                size = Size(width = carbsWidth, height = size.height),
                cornerRadius = CornerRadius(100f)
            )
        } else {

            drawRoundRect(
                color = caloriesExceedColor,
                size = size,
                cornerRadius = CornerRadius(100f)
            )
        }

    }


}

@Preview
@Composable
fun NutrientsBarPreview() {
    NutrientsBar(
        carbs = 10,
        protein = 15,
        fat = 20,
        calories = 150,
        caloriesGoal = 3000,
    )
}
