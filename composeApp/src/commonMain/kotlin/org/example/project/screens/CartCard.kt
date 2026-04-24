package org.example.project.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import kotlinproject.composeapp.generated.resources.eve
import org.example.project.data.local.Carta
import org.jetbrains.compose.resources.painterResource

@Composable
fun CartaCard(

) {
    // 1. Estado para saber si la carta está volteada
    var rotated by remember { mutableStateOf(false) }

    // 2. Animación fluida del ángulo de rotación
    val rotation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "CardRotation"
    )

    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(150.dp, 200.dp) // Tamaño sugerido
            .clickable { rotated = !rotated }
            .graphicsLayer {
                // 3. Aplicamos la rotación en el eje Y
                rotationY = rotation
                cameraDistance = 12f * density // Agrega profundidad (perspectiva)
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        // 4. Decidir qué lado de la carta mostrar
        if (rotation <= 90f) {
            // LADO FRONTAL (Imagen o Color de fondo de la carta cerrada)
            Box(
                Modifier.fillMaxSize().background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = "Example",
                    modifier = Modifier.fillMaxSize()
                )
            }
        } else {
            // LADO TRASERO (La imagen real)
            // IMPORTANTE: Rotamos el contenido 180° para que no se vea en espejo
            Box(
                Modifier.fillMaxSize().graphicsLayer { rotationY = 180f },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.eve),
                    contentDescription = "Example",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}