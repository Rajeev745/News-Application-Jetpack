package com.example.insightfeed.domain.usecase.news

data class NewsUseCase(
    val getNewsUseCase: GetNewsUseCase,
    val getSearchNewsUseCase: SearchNewsUseCase,
    val insertArticleUseCase: InsertArticleUseCase,
    val deleteArticleUseCase: DeleteArticleUseCase,
    val getArticleUseCase: GetArticleUseCase,
    val getArticleListUseCase: GetArticleListUseCase
)