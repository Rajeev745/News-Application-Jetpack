package com.example.insightfeed.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.insightfeed.domain.model.news.ArticlesModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(articlesModel: ArticlesModel)

    @Delete
    suspend fun deleteArticle(articlesModel: ArticlesModel)

    @Query("SELECT * FROM ArticlesModel")
    fun getArticles(): Flow<List<ArticlesModel>>

    @Query("SELECT * FROM ArticlesModel WHERE url=:url")
    fun getArticleFromURL(url: String): ArticlesModel
}