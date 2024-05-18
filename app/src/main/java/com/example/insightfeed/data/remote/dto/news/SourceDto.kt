package com.example.insightfeed.data.remote.dto.news

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SourceDto(
    @SerializedName("id") @Expose var id: String? = "",
    @SerializedName("name") @Expose var name: String? = ""
)