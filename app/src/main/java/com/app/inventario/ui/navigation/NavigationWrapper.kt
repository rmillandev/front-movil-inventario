package com.app.inventario.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.app.inventario.ui.screen.HomeScreen
import com.app.inventario.ui.screen.auth.LoginScreen

@Composable
fun NavigationWrapper() {

    val backStack = rememberNavBackStack(Routes.Login)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.back() },
        entryProvider = entryProvider {
            entry<Routes.Login> {
                LoginScreen(
                    navigateToHome = {
                        backStack.navigateTo(Routes.Home)
                    }
                )
            }
            entry<Routes.Home> {
                HomeScreen(
                    onLogout = {
                        backStack.clear()
                        backStack.navigateTo(Routes.Login)
                    }
                )
            }
        }
    )

}