package org.example.project.navigation

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

//para ejecutar en diferentes plataformas
val navConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(Route.MainMenu::class, Route.MainMenu.serializer())
            subclass(Route.Pantalla2::class, Route.Pantalla2.serializer())
            subclass(Route.Pantalla3::class, Route.Pantalla3.serializer())
        }
    }
}