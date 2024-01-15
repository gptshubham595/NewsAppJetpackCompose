package com.newsApp.domain.usecases.onboarding

import com.newsApp.common.Utils.Either
import com.newsApp.domain.BaseUseCase
import com.newsApp.domain.models.onboarding.PageEntity
import com.newsApp.domain.repositories.onboarding.OnBoardingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

class GetOnBoardingPageListUseCase @Inject constructor(private val onBoardingRepository: OnBoardingRepository) :
    BaseUseCase<Unit, Flow<List<PageEntity>>>() {
    override fun run(params: Unit): Either<Exception, Flow<List<PageEntity>>> {
        onBoardingRepository.getOnBoardingData().let {
            return Either.Success(it)
        }
    }
}