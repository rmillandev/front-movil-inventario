package com.app.inventario.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes: NavKey {

    @Serializable
    data object Login: Routes()

    @Serializable
    data object Home: Routes()

}