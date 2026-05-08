package org.example.project.data.local

import org.jetbrains.compose.resources.DrawableResource

data class Carta(
    val id: Int,
    val imagenFrontal: DrawableResource,
    var estaBocaArriba: Boolean = false,
    var estaEmparejada: Boolean = false
)