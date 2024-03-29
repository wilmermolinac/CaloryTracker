package com.wamcstudios.calorytracker.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.wamcstudios.aifusion.navigation.utils.navigate
import com.wamcstudios.calorytracker.navigation.routes.NavigationGraphRoute
import com.wamcstudios.calorytracker.navigation.routes.NavigationRoute
import com.wamcstudios.calorytracker.onboarding.presentation.activity.ActivityScreen
import com.wamcstudios.calorytracker.onboarding.presentation.age.AgeScreen
import com.wamcstudios.calorytracker.onboarding.presentation.gender.GenderScreen
import com.wamcstudios.calorytracker.onboarding.presentation.goal.GoalScreen
import com.wamcstudios.calorytracker.onboarding.presentation.height.HeightScreen
import com.wamcstudios.calorytracker.onboarding.presentation.nutrient.NutrientScreen
import com.wamcstudios.calorytracker.onboarding.presentation.weight.WeightScreen
import com.wamcstudios.calorytracker.onboarding.presentation.welcome.WelcomeScreen

fun NavGraphBuilder.onboardingNavGraph(navHostController: NavHostController) {
    navigation(
        startDestination = NavigationRoute.Welcome.route,
        route = NavigationGraphRoute.OnboardingGraph.route, builder = {

            composable(route = NavigationRoute.Welcome.route) {

                WelcomeScreen(onNavigate = navHostController::navigate)
            }

            composable(route = NavigationRoute.Gender.route) {

                GenderScreen(onNavigate = navHostController::navigate)

            }

            composable(route = NavigationRoute.Age.route) {
                AgeScreen(onNavigate = navHostController::navigate)

            }

            composable(route = NavigationRoute.Height.route) {
                HeightScreen(navHostController::navigate)
            }

            composable(route = NavigationRoute.Weight.route) {
                WeightScreen(navHostController::navigate)
            }

            composable(route = NavigationRoute.Activity.route) {
                ActivityScreen(onNavigate = navHostController::navigate)
            }

            composable(route = NavigationRoute.Goal.route) {
                GoalScreen(onNavigate = navHostController::navigate)
            }

            composable(route = NavigationRoute.NutrientGoal.route) {

                NutrientScreen(onNavigate = navHostController::navigate)

            }

        }
    )

}