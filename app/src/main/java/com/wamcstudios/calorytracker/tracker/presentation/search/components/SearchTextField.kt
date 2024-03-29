package com.wamcstudios.calorytracker.tracker.presentation.search.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wamcstudios.calorytracker.R

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    hint: String = stringResource(id = R.string.search),
    shouldShowHint: Boolean = false,
    onFocusChange: (FocusState) -> Unit,
    focusManager: FocusManager,
) {

    Box(modifier = modifier.fillMaxWidth()) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusChange(it)
                }
                .shadow(elevation = 2.dp, shape = CircleShape), shape = CircleShape,
            value = value,
            onValueChange = { onValueChange(it) },
            trailingIcon = {
                IconButton(onClick = {
                    focusManager.clearFocus()
                    onSearch()
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.search)
                    )
                }
            },
            singleLine = true,
            textStyle = MaterialTheme.typography.titleMedium,
            placeholder = {
                Text(text = hint)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent, cursorColor = Color.Black
            ),
            keyboardOptions = KeyboardOptions(autoCorrect = false, imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                focusManager.clearFocus()
                onSearch()
            })
        )

    }

}

@Preview
@Composable
fun SearchTextFieldPreview() {
    SearchTextField(
        value = "",
        onValueChange = {},
        onSearch = {},
        onFocusChange = {},
        focusManager = LocalFocusManager.current
    )
}