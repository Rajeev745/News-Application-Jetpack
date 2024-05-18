package com.example.insightfeed.presentation.search

sealed class SearchEvent {
    data class updateSearchQuery(val query: String): SearchEvent()
    object SearchNews: SearchEvent()
}