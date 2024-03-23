package com.wamcstudios.calorytracker.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.wamcstudios.calorytracker.navigation.routes.NavigationGraphRoute
import com.wamcstudios.calorytracker.navigation.routes.NavigationRoute

fun NavGraphBuilder.trackerGraph(navHostController: NavHostController) {

    navigation(
        startDestination = NavigationRoute.TrackerOverview.route,
        route = NavigationGraphRoute.TrackerGraph.route
    ){

        composable(route = NavigationRoute.TrackerOverview.route){


        }


    }

}