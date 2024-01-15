package com.newsApp.core.managers

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.newsApp.data.remote.cache.PreferencesKeys
import com.newsApp.data.remote.cache.dataStore
import com.newsApp.domain.manager.LocalUserManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(
    @ApplicationContext private val context: Context
) : LocalUserManager {
    override suspend fun saveAppEntry(token: String): String {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.APP_ENTRY] = token
        }.also {
            return token
        }
    }

    override fun getAppEntry(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.APP_ENTRY] ?: null
        }
    }
}

