package com.app.inventario.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.app.inventario.data.remote.dto.auth.AuthLoginResponseDto
import kotlinx.coroutines.flow.map

class UserDataStore(private val context: Context) {

    private val Context.dataStore by preferencesDataStore(name = "user_preferences")

    suspend fun saveUserData(authResponse: AuthLoginResponseDto) {
        context.dataStore.edit { preferences ->
            preferences[UserPreferencesKeys.TOKEN] = authResponse.token
            preferences[UserPreferencesKeys.USERNAME] = authResponse.username
            preferences[UserPreferencesKeys.ROL] = authResponse.rol
            preferences[UserPreferencesKeys.EMAIL] = authResponse.email
        }
    }

    val token = context.dataStore.data.map { it[UserPreferencesKeys.TOKEN] ?: "" }
    val username = context.dataStore.data.map { it[UserPreferencesKeys.USERNAME] ?: "N/A" }
    val rol = context.dataStore.data.map { it[UserPreferencesKeys.ROL] ?: "N/A" }
    val email = context.dataStore.data.map { it[UserPreferencesKeys.EMAIL] ?: "N/A" }

    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}