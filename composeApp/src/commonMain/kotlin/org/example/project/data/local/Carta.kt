package org.example.project.data.local

data class Carta(
    val id: Int,
    val imagenFrontal: String,
    val imageRevers: String,
    var estaBocaArriba: Boolean = false,
    var estaEmparejada: Boolean = false
)