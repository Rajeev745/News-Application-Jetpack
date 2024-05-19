package com.example.insightfeed.presentation.news_navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.insightfeed.R
import com.example.insightfeed.domain.model.news.ArticlesModel
import com.example.insightfeed.presentation.bookmark.BookMarkScreen
import com.example.insightfeed.presentation.bookmark.BookMarkViewModel
import com.example.insightfeed.presentation.detail_screen.DetailViewModel
import com.example.insightfeed.presentation.detail_screen.DetailsScreen
import com.example.insightfeed.presentation.home.HomeScreen
import com.example.insightfeed.presentation.home.NewsHomeViewModel
import com.example.insightfeed.presentation.navigation.Route
import com.example.insightfeed.presentation.search.SearchScreen
import com.example.insightfeed.presentation.search.SearchViewModel
import com.example.insightfeed.utils.ArgumentKeys.ARTICLE

@Composable
fun NewsNavigator() {

    val bottomNavigationItem = remember {
        listOf(
            NewsNavigationItem(icon = R.drawable.ic_baseline_home_24, title = "Home"),
            NewsNavigationItem(icon = R.drawable.ic_baseline_search_24, title = "Search"),
            NewsNavigationItem(icon = R.drawable.ic_baseline_bookmark_border_24, title = "Bookmark")
        )
    }

    val navController = rememberNavController()
    val currentBackStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem = when (currentBackStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookMarkScreen.route -> 2
        else -> 0
    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        NewsBottomNavigation(
            items = bottomNavigationItem,
            selectedItem = selectedItem,
            onItemClick = { index ->
                when (index) {
                    0 -> navigateToTab(
                        navController = navController,
                        route = Route.HomeScreen.route
                    )

                    1 -> navigateToTab(
                        navController = navController,
                        route = Route.SearchScreen.route
                    )

                    2 -> navigateToTab(
                        navController = navController,
                        route = Route.BookMarkScreen.route
                    )
                }
            })
    }) {
        val bottomNavigationPadding = it.calculateBottomPadding()

        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomNavigationPadding)
        ) {
            composable(route = Route.HomeScreen.route) { backStackEntry ->
                val viewModel: NewsHomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articlesModel = articles,
                    navigateToDetail = { article ->
                        navigateToDetail(
                            navController = navController,
                            articlesModel = article
                        )
                    }
                )
            }

            composable(route = Route.SearchScreen.route) { backStackEntry ->
                val viewmodel: SearchViewModel = hiltViewModel()
                val state = viewmodel.searchState.value
                SearchScreen(state = state, event = viewmodel::onEvent,
                    navigate = { articlesModel ->
                        navigateToDetail(
                            navController = navController,
                            articlesModel = articlesModel
                        )
                    }
                )
            }

            composable(route = Route.BookMarkScreen.route) { navBackStackEntry ->
                val viewModel: BookMarkViewModel = hiltViewModel()
                val state = viewModel.bookMarkState.value
                BookMarkScreen(state = state, navigateToDetails = { articlesModel ->
                    navigateToDetail(navController = navController, articlesModel = articlesModel)
                })
            }

            composable(route = Route.DetailScreen.route) {
                val viewModel: DetailViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<ArticlesModel>(ARTICLE)
                    ?.let { article ->
                        DetailsScreen(
                            articlesModel = article,
                            event = viewModel::onEvent,
                            navigateUp = {
                                navController.navigateUp()
                            }
                        )
                    }
            }
        }
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route = route) {
        navController.graph.startDestinationRoute?.let { screenRoute ->
            popUpTo(screenRoute) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetail(navController: NavController, articlesModel: ArticlesModel?) {
    navController.currentBackStackEntry?.savedStateHandle?.set(ARTICLE, articlesModel)
    navController.navigate(
        route = Route.DetailScreen.route
    )
}