@file:OptIn(ExperimentalMaterial3Api::class)

package com.app.inventario.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.inventario.ui.navigation.RouteItem
import com.app.inventario.ui.navigation.Routes
import com.app.inventario.ui.screen.categoria.CategoriaScreen
import com.app.inventario.ui.screen.movimientos.MovimientosScreen
import com.app.inventario.ui.screen.producto.ProductoScreen
import com.app.inventario.ui.screen.proveedor.ProveedorScreen
import com.app.inventario.ui.screen.usuario.UsuarioScreen
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    onLogout: () -> Unit
) {

    var selectedRoute by remember {
        mutableStateOf<Routes>(Routes.Movimientos)
    }

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                currentRoute = selectedRoute,
                onDestinationClick = { route ->
                    selectedRoute = route
                    scope.launch { drawerState.close() }
                },
                onLogout = {
                    scope.launch {
                        drawerState.close()
                        onLogout()
                    }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                HomeTopBar {
                    scope.launch { drawerState.open() }
                }
            }
        ) { padding ->
            Column(Modifier.padding(padding)) {
                when (selectedRoute) {
                    Routes.Movimientos -> MovimientosScreen()
                    Routes.Productos -> ProductoScreen()
                    Routes.Categorias -> CategoriaScreen()
                    Routes.Proveedores -> ProveedorScreen()
                    Routes.Usuarios -> UsuarioScreen()
                    else -> {}
                }
            }
        }
    }

}

@Composable
fun AppDrawer(
    currentRoute: Routes?,
    onDestinationClick: (Routes) -> Unit,
    onLogout: () -> Unit
) {
    val items = listOf(
        RouteItem(title = "Movimientos", route = Routes.Movimientos),
        RouteItem(title = "Productos", route = Routes.Productos),
        RouteItem(title = "Categorias", route = Routes.Categorias),
        RouteItem(title = "Proveedores", route = Routes.Proveedores),
        RouteItem(title = "Usuarios", route = Routes.Usuarios)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Inventario",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurface
        )

        HorizontalDivider()

        items.forEach { item ->
            NavigationDrawerItem(
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = { onDestinationClick(item.route) }
            )
        }

        Spacer(Modifier.weight(1f))

        HorizontalDivider()

        Spacer(Modifier.height(8.dp))

        NavigationDrawerItem(
            label = { Text("Cerrar Sesion") },
            selected = false,
            onClick = { onLogout() }
        )

        Spacer(Modifier.navigationBarsPadding())
    }
}

@Composable
fun HomeTopBar(
    onMenuClick: () -> Unit
) {
    TopAppBar(
        title = { Text("Inventario") },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(Icons.Default.Menu, contentDescription = null)
            }
        }
    )
}










