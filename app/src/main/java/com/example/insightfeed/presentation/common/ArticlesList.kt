package com.example.insightfeed.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.insightfeed.domain.model.news.ArticlesModel
import com.example.insightfeed.presentation.Dimens.paddingMedium1
import com.example.insightfeed.presentation.Dimens.smallPadding

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: List<ArticlesModel>,
    onClick: (ArticlesModel) -> Unit
) {


    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(paddingMedium1),
        contentPadding = PaddingValues(all = smallPadding)
    ) {
        items(count = articles.size) {
            val article = articles[it]
            ArticlesCard(articlesModel = article, onClick = { onClick(article) })
        }
    }
}

@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<ArticlesModel>,
    onClick: (ArticlesModel) -> Unit
) {

    val handlePagingResult = handlePagingResult(articles = articles)

    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(paddingMedium1),
            contentPadding = PaddingValues(all = smallPadding)
        ) {
            items(count = articles.itemCount) {
                articles[it]?.let { article ->
                    ArticlesCard(articlesModel = article, onClick = { onClick(article) })
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(articles: LazyPagingItems<ArticlesModel>): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}

@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(paddingMedium1)) {
        repeat(10) {
            ArticlesCardShimmerEffect(
                modifier = Modifier.padding(horizontal = paddingMedium1)
            )
        }
    }
}