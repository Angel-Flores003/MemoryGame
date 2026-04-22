package org.example.project.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Route: NavKey {
    @Serializable
    data object MainMenu : Route()
    @Serializable
    data object Pantalla2 : Route()
    @Serializable
    data class Pantalla3(val userId: String) : Route()
}