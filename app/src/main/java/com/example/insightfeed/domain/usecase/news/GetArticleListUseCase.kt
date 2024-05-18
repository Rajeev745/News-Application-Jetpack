package com.example.insightfeed.domain.usecase.news

import com.example.insightfeed.data.local.NewsDao
import com.example.insightfeed.domain.model.news.ArticlesModel
import kotlinx.coroutines.flow.Flow

class GetArticleListUseCase(private val newsDao: NewsDao) {

    operator fun invoke(): Flow<List<ArticlesModel>> {
        return newsDao.getArticles()
    }
}