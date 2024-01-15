package com.newsApp.presentation.onboarding.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsApp.domain.models.onboarding.PageEntity
import com.newsApp.domain.usecases.onboarding.GetOnBoardingPageListUseCase
import com.newsApp.domain.usecases.onboarding.GetUserAppEntryUseCase
import com.newsApp.domain.usecases.onboarding.SaveUserAppEntryUseCase
import com.newsApp.presentation.onboarding.OnBoardingEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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