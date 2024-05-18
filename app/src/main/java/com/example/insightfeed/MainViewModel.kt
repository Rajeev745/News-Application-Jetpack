package com.example.insightfeed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.insightfeed.domain.usecase.app_entry.AppEntryUseCase
import com.example.insightfeed.presentation.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCase: AppEntryUseCase
) : ViewModel() {

    private val _splashCondition = mutableStateOf(true)
    val splashCondition: State<Boolean> = _splashCondition

    private val _startCondition = mutableStateOf(Route.AppStartNavigation.route)
    val startDestination: State<String> = _startCondition

    init {
        appEntryUseCase.readAppEntryUseCase().onEach { shouldStartFromHome ->
            if (shouldStartFromHome) {
                _startCondition.value = Route.NewsNavigation.route
            } else {
                _startCondition.value = Route.AppStartNavigation.route
            }
            delay(200)
            _splashCondition.value = false
        }.launchIn(viewModelScope)
    }

}