package com.newsApp.domain.usecases.onboarding

import com.newsApp.common.Utils
import com.newsApp.domain.BaseUseCase
import com.newsApp.domain.manager.LocalUserManager
import javax.inject.Inject

class SaveUserAppEntryUseCase @Inject constructor(
    private val localUserManager: LocalUserManager
) : BaseUseCase<String, String>() {
    override suspend fun run(params: String): Utils.Either<Exception, String> {
        return Utils.Either.Success(localUserManager.saveAppEntry(params))
    }
}