package com.wamcstudios.calorytracker.tracker.presentation.tracker_overview.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wamcstudios.calorytracker.R
import com.wamcstudios.calorytracker.ui.theme.LocalSpacing

@Composable
fun AddButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    color: Color = MaterialTheme.colorScheme.primary,
) {

    val spacing = LocalSpacing.current

    OutlinedButton(
        modifier = modifier,
        onClick = { onClick() },
        border = BorderStroke(1.dp, color = color)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.add)
            )

            Spacer(modifier = Modifier.width(spacing.spaceSmall))

            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )


        }


    }
}

@Preview
@Composable
fun AddButtonPreview() {
    AddButton(text = "Add", onClick = {})
}