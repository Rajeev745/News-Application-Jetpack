package com.example.insightfeed.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.insightfeed.domain.model.news.SourceModel

@ProvidedTypeConverter
class NewsTypeConverter {

    @TypeConverter
    fun sourceToString(sourceModel: SourceModel): String {
        return "${sourceModel.id},${sourceModel.name}"
    }

    @TypeConverter
    fun stringToSource(sourceModel: String): SourceModel {
        return sourceModel.split(",").let { sourceArray ->
            SourceModel(id = sourceArray[0], name = sourceArray[1])
        }
    }
}