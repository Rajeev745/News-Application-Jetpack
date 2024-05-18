package com.example.insightfeed.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.insightfeed.data.remote.api.NewsApi
import com.example.insightfeed.domain.mapper.NewsResponseMapper
import com.example.insightfeed.domain.model.news.ArticlesModel
import com.example.insightfeed.utils.Constants.API_KEY

class NewsPagingSource(
    private val newsApi: NewsApi
) : PagingSource<Int, ArticlesModel>() {

    override fun getRefreshKey(state: PagingState<Int, ArticlesModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition = anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlesModel> {
        val page = params.key ?: 1

        return try {
            val newsResponse = newsApi.getNews(page = page, apiKey = API_KEY)
            val newsResponseModel = NewsResponseMapper().mapNewsResponseDtoToModel(newsResponse)
            totalNewsCount += newsResponseModel.articlesDto?.size ?: 0
            val articlesModel = newsResponseModel.articlesDto?.distinctBy { it.title }.orEmpty()
            LoadResult.Page(
                data = articlesModel,
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }
}