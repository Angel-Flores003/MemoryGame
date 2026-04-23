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
            subclass(Route.GameMenu::class, Route.GameMenu.serializer())
            subclass(Route.Stats::class, Route.Stats.serializer())
        }
    }
}