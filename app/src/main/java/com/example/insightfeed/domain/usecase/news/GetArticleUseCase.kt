package com.example.insightfeed.domain.usecase.news

import com.example.insightfeed.data.local.NewsDao
import com.example.insightfeed.domain.model.news.ArticlesModel

class GetArticleUseCase(private val newsDao: NewsDao) {

    operator fun invoke(url: String): ArticlesModel {
        return newsDao.getArticleFromURL(url)
    }
}