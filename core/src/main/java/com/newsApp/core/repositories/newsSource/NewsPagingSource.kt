package com.newsApp.core.repositories.newsSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.newsApp.core.transformer.toDomain
import com.newsApp.data.remote.api.NewsApi
import com.newsApp.domain.models.newsScreen.ArticleEntity
import javax.inject.Inject

class NewsPagingSource @Inject constructor(
    private val newsApi: NewsApi,
    private val source: String,
) : PagingSource<Int, ArticleEntity>() {

    private var totalNewsCount = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleEntity> {
        val page = params.key ?: 1
        return try {
            val newsResponse = newsApi.getNews(page, source)
            totalNewsCount += newsResponse.articles.size
            val articleData = newsResponse.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articleData.toDomain(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (newsResponse.articles.isEmpty() ||
                    totalNewsCount == newsResponse.totalResults
                ) null else page + 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}