package com.example.insightfeed.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.insightfeed.presentation.bookmark.BookMarkScreen
import com.example.insightfeed.presentation.bookmark.BookMarkState
import com.example.insightfeed.presentation.bookmark.BookMarkViewModel
import com.example.insightfeed.presentation.home.HomeScreen
import com.example.insightfeed.presentation.home.NewsHomeViewModel
import com.example.insightfeed.presentation.news_navigation.NewsNavigator
import com.example.insightfeed.presentation.onboarding.OnBoardingScreen
import com.example.insightfeed.presentation.onboarding.viewmodel.OnBoardingViewModel
import com.example.insightfeed.presentation.search.SearchScreen
import com.example.insightfeed.presentation.search.SearchViewModel

@Composable
fun NavGraph(
    startDestination: String
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(event = onBoardingViewModel::onEvent)
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(route = Route.NewsNavigatorScreen.route) {
                NewsNavigator()
            }
        }
    }
}