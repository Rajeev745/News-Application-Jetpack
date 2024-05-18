package com.example.insightfeed.domain.repository

import androidx.paging.PagingData
import com.example.insightfeed.domain.model.news.ArticlesModel
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(): Flow<PagingData<ArticlesModel>>

    fun getSearchNews(q: String): Flow<PagingData<ArticlesModel>>
}