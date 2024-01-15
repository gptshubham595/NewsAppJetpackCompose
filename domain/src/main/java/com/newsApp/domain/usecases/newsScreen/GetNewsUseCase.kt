package com.newsApp.domain.usecases.newsScreen

import androidx.paging.PagingData
import com.newsApp.common.Utils
import com.newsApp.domain.BaseUseCase
import com.newsApp.domain.models.newsScreen.ArticleEntity
import com.newsApp.domain.repositories.newsScreen.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) : BaseUseCase<List<String>, Flow<PagingData<ArticleEntity>>>() {
    override suspend fun run(params: List<String>): Utils.Either<Exception, Flow<PagingData<ArticleEntity>>> {
        return newsRepository.getNews(params).let {
            Utils.Either.Success(it)
        }
    }
}