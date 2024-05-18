package com.example.insightfeed.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.insightfeed.domain.usecase.news.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    private var _searchState = mutableStateOf(SearchState())
    val searchState: State<SearchState> = _searchState

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.updateSearchQuery -> {
                _searchState.value = _searchState.value.copy(searchQuery = event.query)
            }

            is SearchEvent.SearchNews -> {
                searchNews()
            }
        }
    }

    private fun searchNews() {
        val articles =
            newsUseCase.getSearchNewsUseCase(searchQuery = _searchState.value.searchQuery)
                .cachedIn(viewModelScope)
        _searchState.value = _searchState.value.copy(articles = articles)
    }

}