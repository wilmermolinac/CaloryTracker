package com.wamcstudios.calorytracker.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.wamcstudios.calorytracker.navigation.routes.NavigationGraphRoute

@Composable
fun RootNavGraph(
    navHostController: NavHostController,
    startGraphDestination: NavigationGraphRoute,
) {

    NavHost(
        navController = navHostController,
        route = NavigationGraphRoute.RootGraph.route,
        startDestination = startGraphDestination.route,
        builder = {

            onboardingNavGraph(navHostController)

        })
}