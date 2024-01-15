package com.newsApp.presentation.onboarding.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsApp.domain.models.onboarding.PageEntity
import com.newsApp.domain.usecases.onboarding.GetOnBoardingPageListUseCase
import com.newsApp.domain.usecases.onboarding.GetUserAppEntryUseCase
import com.newsApp.domain.usecases.onboarding.SaveUserAppEntryUseCase
import com.newsApp.presentation.navgraph.Route
import com.newsApp.presentation.onboarding.OnBoardingEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val getOnBoardingPageListUseCase: GetOnBoardingPageListUseCase,
    private val getUserAppEntryUseCase: GetUserAppEntryUseCase,
    private val saveUserAppEntryUseCase: SaveUserAppEntryUseCase
) : ViewModel() {

    private val _onBoardingPageListLiveData = MutableLiveData<List<PageEntity>>()
    val onBoardingPageListLiveData = _onBoardingPageListLiveData as LiveData<List<PageEntity>>

    private val _onBoardingUserTokenLiveData = MutableLiveData<String>()
    val onBoardingUserTokenLiveData = _onBoardingUserTokenLiveData as LiveData<String>

    private val _splashCondition = mutableStateOf(true)
    val splashCondition = _splashCondition as State<Boolean>

    var startDestination: Route by mutableStateOf(Route.AppStartNavigation)
        private set

    init {
        getUserAppEntryUseCase.invoke(
            scope = viewModelScope,
            params = Unit,
            onSuccess = {
                it.onEach { shouldStartFromHomeScreenToken ->
                    Log.w("tokenGot", "$shouldStartFromHomeScreenToken")
                    if (!shouldStartFromHomeScreenToken.isNullOrBlank()) {
                        startDestination = Route.NewsNavigation
                    } else {
                        startDestination = Route.AppStartNavigation
                    }
                    delay(300)
                    _splashCondition.value = false
                }.launchIn(viewModelScope)
            },
            onFailure = {})
    }

    fun getOnBoardingPageList() {
        getOnBoardingPageListUseCase.invoke(
            scope = viewModelScope,
            params = Unit,
            onSuccess = {
                viewModelScope.launch(Dispatchers.IO) {
                    it.collect {
                        _onBoardingPageListLiveData.postValue(it)
                    }
                }
            },
            onFailure = {

            })
    }

    fun saveUserAppEntry(token: String) {
        saveUserAppEntryUseCase.invoke(
            scope = viewModelScope,
            params = token,
            onSuccess = {
                Log.w("tokenSaved", "$token")
            },
            onFailure = {
            })
    }

    fun getUserAppEntry() {
        getUserAppEntryUseCase.invoke(
            scope = viewModelScope,
            params = Unit,
            onSuccess = {
                viewModelScope.launch(Dispatchers.IO) {
                    it.collect {
                        _onBoardingUserTokenLiveData.postValue(it)
                    }
                }
            },
            onFailure = {
            })
    }

    fun onEvent(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.GetAppEntry -> getUserAppEntry()
            is OnBoardingEvent.SaveAppEntry -> saveUserAppEntry(event.token)
        }
    }
}