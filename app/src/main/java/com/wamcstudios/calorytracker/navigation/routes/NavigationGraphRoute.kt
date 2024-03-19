package com.wamcstudios.calorytracker.navigation.routes

sealed class NavigationGraphRoute(val route: String) {

    object RootGraph : NavigationGraphRoute(route = "root_graph")

    object OnboardingGraph : NavigationGraphRoute(route = "onboarding_graph")

}