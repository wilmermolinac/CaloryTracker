package com.wamcstudios.calorytracker.onboarding.presentation.welcome

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wamcstudios.calorytracker.navigation.routes.NavigationRoute
import com.wamcstudios.calorytracker.navigation.utils.UiEvent
import com.wamcstudios.calorytracker.onboarding.presentation.welcome.components.WelcomeContent

@Composable
fun WelcomeScreen(onNavigate: (UiEvent) -> Unit) {

    Scaffold {
        WelcomeContent(
            modifier = Modifier.padding(it),
            onClick = { onNavigate(UiEvent.Navigate(route = NavigationRoute.Gender.route)) })
    }
}