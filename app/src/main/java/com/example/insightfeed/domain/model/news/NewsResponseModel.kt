package com.example.insightfeed.domain.model.news

data class NewsResponseModel(
    val status: String?,
    val totalResults: Int?,
    val articlesDto: List<ArticlesModel>?
)