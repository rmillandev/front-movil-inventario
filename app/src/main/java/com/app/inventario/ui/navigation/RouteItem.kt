package com.app.inventario.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class RouteItem(

    val title: String,

    val icon: ImageVector? = null,

    val badgeCount: Int? = null,

    val route: Routes

)
