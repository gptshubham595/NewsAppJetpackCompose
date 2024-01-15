package com.newsApp.presentation.onboarding.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.newsApp.presentation.onboarding.OnBoardingEvent
import com.newsApp.presentation.onboarding.ui.components.OnBoardingPageUI
import com.newsApp.presentation.onboarding.ui.components.PageIndicator
import com.newsApp.presentation.onboarding.viewmodels.OnBoardingViewModel
import com.newsApp.presentation.ui.Dimensions.MediumPadding
import com.newsApp.presentation.ui.Dimensions.PagerIndicatorWidth
import com.newsApp.presentation.ui.common.NewsButton
import com.newsApp.presentation.ui.common.NewsTextButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    onBoardingViewModel: OnBoardingViewModel = viewModel(),
    event: (OnBoardingEvent) -> Unit
) {
    // Observe the LiveData from the ViewModel
    val onBoardingPageList = onBoardingViewModel.onBoardingPageListLiveData.observeAsState()
    // Fetch data if not already loaded
    LaunchedEffect(onBoardingViewModel) {
        onBoardingViewModel.getOnBoardingPageList()
    }

    // Display loading or error state if needed
    if (onBoardingPageList.value == null) {
        // Display loading state
        // You can create a loading composable or simply return an empty Box
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            val pagerState = rememberPagerState(initialPage = 0) {
                onBoardingPageList.value?.size ?: 0
            }
            val buttonState = remember {
                derivedStateOf {
                    when (pagerState.currentPage) {
                        0 -> listOf("", "Next")
                        onBoardingPageList.value?.size?.minus(1) -> listOf("Back", "Get Started")
                        else -> listOf("Back", "Next")
                    }
                }
            }

            HorizontalPager(
                state = pagerState,
            ) { index ->
                onBoardingPageList.value?.get(index)?.let { OnBoardingPageUI(page = it) }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MediumPadding)
                    .navigationBarsPadding(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PageIndicator(
                    modifier = Modifier.width(PagerIndicatorWidth),
                    pageSize = onBoardingPageList.value?.size ?: 0,
                    selectedPage = pagerState.currentPage
                )
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val scope = rememberCoroutineScope()

                    if (buttonState.value[0].isNotEmpty()) {
                        NewsTextButton(text = buttonState.value[0]) {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    }
                    NewsButton(text = buttonState.value[1]) {
                        if (pagerState.currentPage == onBoardingPageList.value?.size?.minus(1)) {
                            // Navigate to next screen
                            event(OnBoardingEvent.SaveAppEntry("random_token"))
                        } else {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(0.5f))
        }
    }
}
