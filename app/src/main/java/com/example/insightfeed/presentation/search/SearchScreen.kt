package com.example.insightfeed.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.insightfeed.domain.model.news.ArticlesModel
import com.example.insightfeed.presentation.Dimens.paddingMedium1
import com.example.insightfeed.presentation.common.ArticlesList
import com.example.insightfeed.presentation.common.SearchBar
import com.example.insightfeed.presentation.navigation.Route

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigate: (ArticlesModel) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(
                top = paddingMedium1,
                start = paddingMedium1,
                end = paddingMedium1
            )
            .statusBarsPadding().fillMaxSize()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onSearch = { event(SearchEvent.SearchNews) },
            onValueChange = {
                event(SearchEvent.updateSearchQuery(it))
            })

        Spacer(modifier = Modifier.height(paddingMedium1))

        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(articles = articles, onClick = { navigate })
        }
    }

}