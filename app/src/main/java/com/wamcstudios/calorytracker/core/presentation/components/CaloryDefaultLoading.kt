package com.wamcstudios.calorytracker.core.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wamcstudios.calorytracker.ui.theme.LocalSpacing

@Composable
fun CaloryDefaultLoading(modifier: Modifier = Modifier, isLoading: Boolean = false) {

    val spacing = LocalSpacing.current

    AnimatedVisibility(visible = isLoading) {

        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            CircularProgressIndicator(modifier = Modifier.size(spacing.iconButtonSizeExtra))

        }

    }

}

@Preview
@Composable
fun CaloryDefaultLoadingPreview() {
    CaloryDefaultLoading(isLoading = true)
}