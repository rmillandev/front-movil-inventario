package com.app.inventario.data.local.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object UserPreferencesKeys {

    val TOKEN = stringPreferencesKey("token")
    val USERNAME = stringPreferencesKey("username")
    val ROL = stringPreferencesKey("rol")
    val EMAIL = stringPreferencesKey("email")

}