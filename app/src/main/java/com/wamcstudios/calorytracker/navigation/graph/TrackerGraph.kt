package com.wamcstudios.calorytracker.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.wamcstudios.aifusion.navigation.utils.navigate
import com.wamcstudios.calorytracker.navigation.routes.NavigationGraphRoute
import com.wamcstudios.calorytracker.navigation.routes.NavigationRoute
import com.wamcstudios.calorytracker.tracker.presentation.search.SearchScreen
import com.wamcstudios.calorytracker.tracker.presentation.tracker_overview.TrackerOverviewScreen

fun NavGraphBuilder.trackerGraph(navHostController: NavHostController) {

    navigation(
        startDestination = NavigationRoute.TrackerOverview.route,
        route = NavigationGraphRoute.TrackerGraph.route
    ) {

        composable(route = NavigationRoute.TrackerOverview.route) {

            TrackerOverviewScreen(onNavigate = navHostController::navigate)
        }


        composable(
            route = NavigationRoute.Search.route,
            arguments = listOf(navArgument(name = "mealTypeName") {
                type = NavType.StringType
                nullable = true
                defaultValue = null
            }, navArgument(name = "dayOfMonth") {
                type = NavType.IntType
                nullable = false
                defaultValue = -1
            }, navArgument(name = "monthValue") {
                type = NavType.IntType
                nullable = false
                defaultValue = -1
            }, navArgument(name = "year") {
                type = NavType.IntType
                nullable = false
                defaultValue = -1
            })
        ) {

            val mealTypeName = it.arguments?.getString("mealTypeName")
            val dayOfMonth = it.arguments?.getInt("dayOfMonth")
            val monthValue = it.arguments?.getInt("monthValue")
            val year = it.arguments?.getInt("year"
            )
            SearchScreen(onNavigate = navHostController::navigate)
        }


    }

}