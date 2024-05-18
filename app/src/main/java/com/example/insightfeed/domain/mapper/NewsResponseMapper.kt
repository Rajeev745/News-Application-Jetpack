package com.example.insightfeed.domain.mapper

import com.example.insightfeed.data.remote.dto.news.ArticlesDto
import com.example.insightfeed.data.remote.dto.news.NewsResponseDto
import com.example.insightfeed.data.remote.dto.news.SourceDto
import com.example.insightfeed.domain.model.news.ArticlesModel
import com.example.insightfeed.domain.model.news.NewsResponseModel
import com.example.insightfeed.domain.model.news.SourceModel

/**
 * Mapper class for news response
 */
class NewsResponseMapper {

    fun mapNewsResponseDtoToModel(dto: NewsResponseDto): NewsResponseModel {
        val articleModelList = dto.articlesDto?.map { mapArticlesDtoToModel(it) }
        return NewsResponseModel(
            status = dto.status,
            totalResults = dto.totalResults,
            articlesDto = articleModelList
        )
    }

    private fun mapArticlesDtoToModel(dto: ArticlesDto): ArticlesModel {
        return ArticlesModel(
            source = sourceDtoToModel(dto.source),
            author = dto.author,
            title = dto.title,
            description = dto.description,
            url = dto.url ?: "",
            urlToImage = dto.urlToImage,
            content = dto.content
        )
    }

    private fun sourceDtoToModel(dto: SourceDto?): SourceModel {
        return SourceModel(
            id = dto?.id,
            name = dto?.name
        )
    }
}