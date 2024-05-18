package com.example.insightfeed.domain.model.news

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticlesModel(
    val source: SourceModel?,
    val author: String?,
    val title: String?,
    val description: String?,
    @PrimaryKey val url: String,
    val urlToImage: String?,
    val content: String?
)