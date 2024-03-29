package com.wamcstudios.calorytracker.onboarding.presentation.welcome.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.wamcstudios.calorytracker.R
import com.wamcstudios.calorytracker.core.presentation.components.CaloryDefaultActionButton
import com.wamcstudios.calorytracker.ui.theme.LocalSpacing

@Composable
fun WelcomeContent(modifier: Modifier = Modifier, onClick: () -> Unit) {

    val spacing = LocalSpacing.current

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(horizontal = spacing.spaceMediumExtra)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.welcome_text),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            CaloryDefaultActionButton(
                onClick = { onClick() },
                text = stringResource(id = R.string.lets_go),
                isEnabled = true
            )


        }

    }

}

@Preview
@Composable
fun WelcomeContentPreview() {
    WelcomeContent(onClick = {})
}