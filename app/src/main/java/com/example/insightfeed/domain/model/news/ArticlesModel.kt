package com.example.insightfeed.domain.model.news

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class ArticlesModel(
    val source: SourceModel?,
    val author: String?,
    val title: String?,
    val description: String?,
    @PrimaryKey val url: String,
    val urlToImage: String?,
    val content: String?
): Parcelable