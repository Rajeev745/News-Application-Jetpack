package com.example.insightfeed.presentation.navigation

sealed class Route(val route: String) {

    object OnBoardingScreen : Route(route = "OnBoardingScreen")
    object HomeScreen : Route(route = "HomeScreen")
    object SearchScreen : Route(route = "SearchScreen")
    object BookMarkScreen : Route(route = "BookMarkScreen")
    object DetailScreen : Route(route = "DetailScreen")
    object AppStartNavigation : Route(route = "AppStartNavigation")
    object NewsNavigation : Route(route = "NewsNavigation")
    object NewsNavigatorScreen : Route(route = "NewsNavigatorScreen")
}