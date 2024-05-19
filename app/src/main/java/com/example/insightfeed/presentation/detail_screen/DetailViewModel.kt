package com.example.insightfeed.presentation.detail_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.insightfeed.domain.model.news.ArticlesModel
import com.example.insightfeed.domain.usecase.news.NewsUseCase
import com.example.insightfeed.utils.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.InsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCase.getArticleUseCase(url = event.articlesModel.url)
                    if (article == null) {
                        insertArticle(articlesModel = event.articlesModel)
                    } else {
                        deleteArticle(articlesModel = event.articlesModel)
                    }
                }
            }

            is DetailEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    private suspend fun deleteArticle(articlesModel: ArticlesModel) {
        newsUseCase.deleteArticleUseCase(articlesModel = articlesModel)
        sideEffect = UIComponent.Toast("Article Removed")
    }

    private suspend fun insertArticle(articlesModel: ArticlesModel) {
        newsUseCase.insertArticleUseCase(articlesModel = articlesModel)
        sideEffect = UIComponent.Toast("Article Inserted")
    }
}