package com.newsApp.data.remote.cache

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.newsApp.common.Constants

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.USER_SETTINGS)

object PreferencesKeys {
    val APP_ENTRY = stringPreferencesKey(Constants.APP_ENTRY)
}