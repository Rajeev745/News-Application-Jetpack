package com.example.insightfeed.presentation.search

import androidx.paging.PagingData
import com.example.insightfeed.domain.model.news.ArticlesModel
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<ArticlesModel>>? = null
)