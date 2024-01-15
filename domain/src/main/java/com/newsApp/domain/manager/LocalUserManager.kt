package com.newsApp.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun saveAppEntry(token: String) : String
    fun getAppEntry(): Flow<String?>
}