package com.wamcstudios.calorytracker.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.wamcstudios.aifusion.navigation.utils.navigate
import com.wamcstudios.calorytracker.navigation.routes.NavigationGraphRoute
import com.wamcstudios.calorytracker.navigation.routes.NavigationRoute
import com.wamcstudios.calorytracker.onboarding.presentation.welcome.WelcomeScreen

fun NavGraphBuilder.onboardingNavGraph(navHostController: NavHostController) {
    navigation(
        startDestination = NavigationRoute.Welcome.route,
        route = NavigationGraphRoute.OnboardingGraph.route, builder = {

            composable(route = NavigationRoute.Welcome.route) {

                WelcomeScreen(onNavigate = navHostController::navigate)
            }

            composable(route = NavigationRoute.Age.route){

            }

        }
    )

}