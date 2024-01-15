package com.newsApp.presentation.onboarding.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsApp.domain.models.onboarding.PageEntity
import com.newsApp.domain.usecases.onboarding.GetOnBoardingPageListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val getOnBoardingPageListUseCase: GetOnBoardingPageListUseCase
) : ViewModel() {

    private val _onBoardingPageListLiveData = MutableLiveData<List<PageEntity>>()
    val onBoardingPageListLiveData = _onBoardingPageListLiveData as LiveData<List<PageEntity>>

    fun getOnBoardingPageList() {
        getOnBoardingPageListUseCase.invoke(
            scope = viewModelScope,
            params = Unit,
            onSuccess = {
                viewModelScope.launch(Dispatchers.IO)
                {
                    it.collect {
                        _onBoardingPageListLiveData.postValue(it)
                    }
                }
            },
            onFailure = {

            })
    }
}