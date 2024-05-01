package com.example.insightfeed.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.insightfeed.domain.manager.LocalUserManagerRepository
import com.example.insightfeed.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerRepositoryImpl(
    private val context: Context
) : LocalUserManagerRepository {

    override suspend fun saveAppEntry() {
        context.datastore.edit { preferences ->
            preferences[PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.datastore.data.map { preferences ->
            preferences[PreferencesKeys.APP_ENTRY] ?: false
        }
    }
}

/*
   Declared lazily will only initialize for the first time when we need it
   Used the preference data store because we need to store it in key value format
*/
private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = Constants.USER_SETTINGS)

/*
   For saving and retrieving values in data store we have to use "Preferences.Key<T>".
   This object is created to hold the keys for the preferences, allowing access in both methods.
*/
private object PreferencesKeys {
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}