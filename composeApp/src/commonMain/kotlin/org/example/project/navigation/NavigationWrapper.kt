package org.example.project.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import org.example.project.screens.Pantalla1Screen
import org.example.project.screens.Pantalla2Screen
import org.example.project.screens.Pantalla3Screen

@Composable
fun NavigationWrapper(){
    val backStack = rememberNavBackStack(navConfig, Route.Pantalla1)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Route.Pantalla1> {
                Pantalla1Screen(
                    navigateTo2 = { backStack.add(Route.Pantalla2) },
                    navigateTo3 = { backStack.add(Route.Pantalla3(userId = "user_42")) }
                )
            }
            entry<Route.Pantalla2> {
                Pantalla2Screen(navigateBack = { backStack.removeLastOrNull() })
            }
            entry<Route.Pantalla3> { key ->
                Pantalla3Screen(userId = key.userId, navigateBack = { backStack.removeLastOrNull() })
            }
        }
    )
}