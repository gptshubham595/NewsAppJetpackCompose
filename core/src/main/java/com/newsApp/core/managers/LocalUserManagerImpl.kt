package com.newsApp.core.managers

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.newsApp.common.Constants
import com.newsApp.common.Constants.USER_SETTINGS
import com.newsApp.domain.manager.LocalUserManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
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

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object PreferencesKeys {
    val APP_ENTRY = stringPreferencesKey(Constants.APP_ENTRY)
}