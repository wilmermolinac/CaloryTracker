package com.wamcstudios.calorytracker.core.presentation.components

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CaloryDefaultActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String = "",
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    isEnabled: Boolean = false,
) {
    Button(modifier = modifier, onClick = { onClick() }, enabled = isEnabled) {
        Text(text = text, style = textStyle.copy(fontWeight = FontWeight.ExtraBold))
    }
}

@Preview
@Composable
fun CaloryDefaultActionButtonPreview() {
    CaloryDefaultActionButton(onClick = {})
}