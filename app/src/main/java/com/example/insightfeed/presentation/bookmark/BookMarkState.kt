package com.example.insightfeed.presentation.bookmark

import com.example.insightfeed.domain.model.news.ArticlesModel

data class BookMarkState(
    val articles: List<ArticlesModel> = emptyList()
)