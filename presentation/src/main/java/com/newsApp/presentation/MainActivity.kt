package com.newsApp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.newsApp.domain.models.onboarding.PageEntity
import com.newsApp.domain.repositories.onboarding.OnBoardingRepository
import com.newsApp.domain.usecases.onboarding.GetOnBoardingPageListUseCase
import com.newsApp.presentation.onboarding.ui.OnBoardingScreen
import com.newsApp.presentation.onboarding.viewmodels.OnBoardingViewModel
import com.newsApp.presentation.ui.theme.NewsAppJCTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val onBoardingViewModel: OnBoardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            NewsAppJCTheme {
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    OnBoardingScreen(onBoardingViewModel)
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        NewsAppJCTheme {
            Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                OnBoardingScreen(onBoardingViewModel)
            }
        }
    }
}
