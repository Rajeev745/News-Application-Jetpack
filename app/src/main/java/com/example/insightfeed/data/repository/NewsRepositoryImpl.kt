package com.example.insightfeed.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.insightfeed.data.remote.api.NewsApi
import com.example.insightfeed.data.remote.paging.NewsPagingSource
import com.example.insightfeed.data.remote.paging.SearchPagingSource
import com.example.insightfeed.domain.model.news.ArticlesModel
import com.example.insightfeed.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val newsApi: NewsApi) : NewsRepository {

    override fun getNews(): Flow<PagingData<ArticlesModel>> {
        return Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
            NewsPagingSource(newsApi = newsApi)
        }).flow
    }

    override fun getSearchNews(q: String): Flow<PagingData<ArticlesModel>> {
        return Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
            SearchPagingSource(newsApi = newsApi, query = q)
        }).flow
    }
}