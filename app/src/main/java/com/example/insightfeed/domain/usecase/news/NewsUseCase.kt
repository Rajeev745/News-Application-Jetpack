package com.example.insightfeed.domain.usecase.news

data class NewsUseCase(
    val getNewsUseCase: GetNewsUseCase,
    val getSearchNewsUseCase: SearchNewsUseCase
)