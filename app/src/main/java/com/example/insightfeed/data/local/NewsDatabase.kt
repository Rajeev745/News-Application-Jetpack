package com.example.insightfeed.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.insightfeed.domain.model.news.ArticlesModel

@Database(entities = [ArticlesModel::class], version = 1)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract val newsDao: NewsDao
}