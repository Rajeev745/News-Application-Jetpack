package com.example.insightfeed.data.remote.dto.news

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsResponseDto(
    @SerializedName("status") @Expose var status: String? = "",
    @SerializedName("totalResults") @Expose var totalResults: Int? = 0,
    @SerializedName("articles") @Expose var articlesDto: ArrayList<ArticlesDto>? = arrayListOf()
)