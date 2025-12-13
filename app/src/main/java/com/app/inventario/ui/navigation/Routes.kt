package com.app.inventario.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes: NavKey {

    @Serializable
    data object Login: Routes()

    @Serializable
    data object Home: Routes()

    @Serializable
    data object Movimientos : Routes()

    @Serializable
    data object Productos : Routes()

    @Serializable
    data object Categorias : Routes()

    @Serializable
    data object Proveedores : Routes()

    @Serializable
    data object Usuarios : Routes()

}