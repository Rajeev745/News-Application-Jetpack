package com.example.insightfeed.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.insightfeed.domain.usecase.news.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsHomeViewModel @Inject constructor(
    newsUseCase: NewsUseCase
) : ViewModel() {

    val news = newsUseCase.getNewsUseCase().cachedIn(viewModelScope)
}