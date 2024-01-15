package com.newsApp.presentation.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.newsApp.presentation.onboarding.ui.OnBoardingScreen
import com.newsApp.presentation.onboarding.viewmodels.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination: Route,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination.route) {
        //add nav graph
        navigation(
            startDestination = Route.OnBoardingScreen.route,
            route = Route.AppStartNavigation.route
        ) {
            composable(Route.OnBoardingScreen.route) {
                val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(onBoardingViewModel = onBoardingViewModel, event = {
                    onBoardingViewModel.onEvent(it)
                })
            }
        }

        navigation(
            startDestination = Route.NewsNavigatorScreen.route,
            route = Route.NewsNavigation.route
        ) {
            composable(Route.NewsNavigatorScreen.route) {
                Text(text = "NewsNavigatorScreen")
            }
        }
    }

}
