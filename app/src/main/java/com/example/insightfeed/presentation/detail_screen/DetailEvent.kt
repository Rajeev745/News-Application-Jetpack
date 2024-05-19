package com.example.insightfeed.presentation.detail_screen

import com.example.insightfeed.domain.model.news.ArticlesModel

sealed class DetailEvent {
    data class InsertDeleteArticle(val articlesModel: ArticlesModel) : DetailEvent()
    object RemoveSideEffect : DetailEvent()
}