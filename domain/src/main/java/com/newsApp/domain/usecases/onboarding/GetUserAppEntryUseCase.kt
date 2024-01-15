package com.newsApp.domain.usecases.onboarding

import com.newsApp.common.Utils
import com.newsApp.domain.BaseUseCase
import com.newsApp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserAppEntryUseCase @Inject constructor(
    private val localUserManager: LocalUserManager
) : BaseUseCase<Unit, Flow<String?>>() {
    override suspend fun run(params: Unit): Utils.Either<Exception, Flow<String?>> {
        return Utils.Either.Success(localUserManager.getAppEntry())
    }
}