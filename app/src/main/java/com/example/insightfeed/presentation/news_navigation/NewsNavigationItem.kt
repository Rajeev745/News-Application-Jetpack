package com.example.insightfeed.presentation.news_navigation

import androidx.annotation.DrawableRes

data class NewsNavigationItem(
    @DrawableRes val icon: Int,
    var title: String
)