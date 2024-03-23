package com.wamcstudios.calorytracker.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wamcstudios.calorytracker.ui.theme.LocalSpacing

@Composable
fun CaloryDefaultTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit, unit: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        autoCorrect = false, keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
    ),
    isKeyboardActionsDone: Boolean = true,
    focusManager: FocusManager, onDone: () -> Unit,
) {
    val spacing = LocalSpacing.current

    val colorPrimary = MaterialTheme.colorScheme.primary

    var isFocused = remember {
        mutableStateOf(false)
    }

    val colorStroke = if (isFocused.value) {

        MaterialTheme.colorScheme.primary
    } else {
        Color.Transparent
    }



    Surface(
        shape = RoundedCornerShape(spacing.spaceSmall),
        color = MaterialTheme.colorScheme.surfaceContainer,
        border = BorderStroke(width = 2.dp, color = colorStroke)
    ) {
        Row(modifier = modifier.padding(spacing.spaceNanoSmall)) {
            BasicTextField(modifier = Modifier
                .width(IntrinsicSize.Min)
                .alignBy(LastBaseline)
                .onFocusChanged {
                    if (it.isFocused) {
                        isFocused.value = true
                    } else {
                        isFocused.value = false
                    }
                },
                value = value,
                onValueChange = { onValueChange(it) },
                textStyle = MaterialTheme.typography.displayLarge.copy(
                    color = colorPrimary, fontWeight = FontWeight.Bold
                ),
                keyboardOptions = keyboardOptions,
                keyboardActions = KeyboardActions(onDone = {

                    if (isKeyboardActionsDone) {
                        focusManager.clearFocus()

                        onDone()
                    }

                }, onNext = {

                    if (isKeyboardActionsDone.not()) {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                }

                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)


            )

            Spacer(modifier = Modifier.width(spacing.spaceNanoSmall))
            Text(
                modifier = Modifier.alignByBaseline(),
                text = unit,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

        }

    }


}

@Preview
@Composable
fun CaloryDefaultTextFieldPreview() {
    CaloryDefaultTextField(
        value = "31",
        onValueChange = {},
        unit = "Age",
        focusManager = LocalFocusManager.current,
        onDone = {},
    )
}