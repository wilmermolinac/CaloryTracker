package com.wamcstudios.calorytracker.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wamcstudios.calorytracker.core.domain.model.Gender
import com.wamcstudios.calorytracker.ui.theme.LocalSpacing

@Composable
fun CaloryDefaultSelectButton(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    isSelected: Boolean,
    onClick: () -> Unit,
) {

    val spacing = LocalSpacing.current

    val background = if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.background
    }

    val textColor = if (isSelected) {
        Color.White
    } else {
        MaterialTheme.colorScheme.primary
    }

    Surface(
        modifier = modifier,
        onClick = { onClick() },
        color = background,
        border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primary),
        shape = CircleShape
    ) {

        Box(contentAlignment = Alignment.Center) {

            Text(
                modifier = Modifier.padding(
                    horizontal = spacing.spaceMedium,
                    vertical = spacing.spaceSmall
                ),
                text = text.uppercase(),
                style = textStyle.copy(
                    color = textColor,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
            )
        }


    }


}

@Preview
@Composable
fun CaloryDefaultSelectButtonPreview() {
    CaloryDefaultSelectButton(text = Gender.Male.name, isSelected = false, onClick = {})
}