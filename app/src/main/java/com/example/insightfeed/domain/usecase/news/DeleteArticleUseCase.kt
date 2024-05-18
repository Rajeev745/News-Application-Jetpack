package com.example.insightfeed.domain.usecase.news

import com.example.insightfeed.data.local.NewsDao
import com.example.insightfeed.domain.model.news.ArticlesModel

class DeleteArticleUseCase(private val newsDao: NewsDao) {

    suspend operator fun invoke(articlesModel: ArticlesModel) {
        newsDao.deleteArticle(articlesModel = articlesModel)
    }
}