package com.wamcstudios.calorytracker.tracker.presentation.tracker_overview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wamcstudios.calorytracker.R
import com.wamcstudios.calorytracker.ui.theme.LocalSpacing

@Composable
fun NutrientBarInfo(
    modifier: Modifier = Modifier,
    value: Int,
    goal: Int,
    name: String,
    color: Color,
    strokeWidth: Dp = 8.dp,
) {

    val spacing = LocalSpacing.current

    val background = MaterialTheme.colorScheme.background
    val goalExceededColor = MaterialTheme.colorScheme.error

    val angleRatio = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = value) {
        angleRatio.animateTo(
            targetValue = if (goal > 0) {
                value / goal.toFloat()
            } else {
                0f
            }, animationSpec = tween(durationMillis = 300)
        )

    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {

            drawArc(
                color = if (value <= goal) {
                    background
                } else {
                    goalExceededColor
                },
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                size = size,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
            )

            if (value <= goal) {
                drawArc(
                    color = color,
                    startAngle = 90f,
                    sweepAngle = 360f * angleRatio.value,
                    useCenter = false,
                    size = size,
                    style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            UnitDisplay(
                amount = value,
                unit = stringResource(id = R.string.grams),
                amountStyle = if (value <= goal) {
                    MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    MaterialTheme.typography.titleLarge.copy(color = goalExceededColor)
                }, unitTextStyle = if (value <= goal) {
                    MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    MaterialTheme.typography.bodySmall.copy(color = goalExceededColor)
                }
            )
            Spacer(modifier = Modifier.height(spacing.spaceMicroSmall))
            Text(
                text = name,
                style = if (value <= goal) {
                    MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    MaterialTheme.typography.titleMedium.copy(color = goalExceededColor)
                }, fontWeight = FontWeight.Bold
            )


        }
    }


}

@Preview
@Composable
fun NutrientBarInfoPreview() {
    NutrientBarInfo(value = 10, goal = 80, name = "Carbs", color = MaterialTheme.colorScheme.primary)
}