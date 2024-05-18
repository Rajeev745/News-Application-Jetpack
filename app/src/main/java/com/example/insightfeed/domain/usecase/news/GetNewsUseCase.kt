package com.example.insightfeed.domain.usecase.news

import androidx.paging.PagingData
import com.example.insightfeed.domain.model.news.ArticlesModel
import com.example.insightfeed.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNewsUseCase(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<PagingData<ArticlesModel>> {
        return newsRepository.getNews()
    }
}