package com.example.insightfeed.domain.usecase

data class AppEntryUseCase(
    val readAppEntryUseCase: ReadAppEntryUseCase,
    val saveAppEntryUseCase: SaveAppEntryUseCase
)