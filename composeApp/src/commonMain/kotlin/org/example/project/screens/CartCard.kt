package org.example.project.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import kotlinproject.composeapp.generated.resources.eve
import kotlinproject.composeapp.generated.resources.hsrlogo
import org.example.project.data.local.Carta
import org.jetbrains.compose.resources.painterResource

@Composable
fun CartaCard(
    carta: Carta,
    onClick: () -> Unit
) {
    // La animación ahora depende de si la carta está boca arriba O si ya fue encontrada
    val rotation by animateFloatAsState(
        targetValue = if (carta.estaBocaArriba || carta.estaEmparejada) 180f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "CardRotation"
    )

    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable(enabled = !carta.estaBocaArriba && !carta.estaEmparejada) { onClick() }
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12f * density
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        if (rotation <= 90f) {
            // LADO GRIS (Cerrada)
            Box(Modifier.fillMaxSize().background(Color.LightGray)){
                Image(
                    painter = painterResource(Res.drawable.hsrlogo),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        } else {
            // LADO IMAGEN (Abierta)
            Box(Modifier.fillMaxSize().graphicsLayer { rotationY = 180f }) {
                Image(
                    painter = painterResource(carta.imagenFrontal),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}