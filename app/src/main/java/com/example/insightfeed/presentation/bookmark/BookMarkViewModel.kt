package com.example.insightfeed.presentation.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.insightfeed.domain.usecase.news.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    private val _bookMarkState = mutableStateOf(BookMarkState())
    val bookMarkState: State<BookMarkState> = _bookMarkState

    init {
        getArticles()
    }

    private fun getArticles() {
        newsUseCase.getArticleListUseCase().onEach {
            _bookMarkState.value = _bookMarkState.value.copy(articles = it)
        }.launchIn(viewModelScope)
    }
}