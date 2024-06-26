package com.wamcstudios.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wamcstudios.calorytracker.navigation.graph.RootNavGraph
import com.wamcstudios.calorytracker.navigation.routes.NavigationGraphRoute
import com.wamcstudios.calorytracker.onboarding.presentation.welcome.WelcomeScreen
import com.wamcstudios.calorytracker.ui.MainViewModel
import com.wamcstudios.calorytracker.ui.theme.CaloryTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var startGraphRouteDestination = viewModel.startDestinationState

        setContent {

            navHostController = rememberNavController()
            CaloryTrackerTheme {
                // A surface container using the 'background' color from the theme
                RootNavGraph(
                    navHostController = navHostController,
                    startGraphDestination = startGraphRouteDestination
                )

            }
        }
    }
}
