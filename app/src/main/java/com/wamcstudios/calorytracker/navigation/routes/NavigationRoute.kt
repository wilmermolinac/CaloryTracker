package com.wamcstudios.calorytracker.navigation.routes

sealed class NavigationRoute(val route: String) {

    object Welcome : NavigationRoute(route = "welcome")
    object Age : NavigationRoute(route = "age")
    object Gender : NavigationRoute(route = "gender")
    object Height : NavigationRoute(route = "height")
    object Weight : NavigationRoute(route = "weight")
    object NutrientGoal : NavigationRoute(route = "nutrient_goal")
    object Activity : NavigationRoute(route = "activity")
    object Goal : NavigationRoute(route = "goal")

    object TrackerOverview : NavigationRoute(route = "tracker_overview")
    object Search :
        NavigationRoute(route = "search/?{mealTypeName}/?{dayOfMonth}/?{monthValue}/?{year}") {
        fun passDate(mealTypeName: String, dayOfMonth: Int, monthValue: Int, year: Int) =
            "search/?$mealTypeName/?$dayOfMonth/?$monthValue/?$year"
    }
}