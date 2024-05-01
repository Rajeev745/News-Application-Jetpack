package com.example.insightfeed

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.insightfeed.domain.usecase.AppEntryUseCase
import com.example.insightfeed.presentation.onboarding.OnBoardingScreen
import com.example.insightfeed.presentation.onboarding.viewmodel.OnBoardingViewModel
import com.example.insightfeed.ui.theme.InsightFeedTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appEntryUseCase: AppEntryUseCase
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            appEntryUseCase.readAppEntryUseCase().collect {
                Log.d("APP ENTRY", "$it")
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            InsightFeedTheme(dynamicColor = false) {
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                    val onBoardingViewModel: OnBoardingViewModel = viewModel()
                    OnBoardingScreen(event = onBoardingViewModel::onEvent)
                }
            }
        }
    }
}