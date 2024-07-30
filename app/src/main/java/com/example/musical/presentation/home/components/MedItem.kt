package com.example.musical.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.musical.R

data class Medication(
    val imageRes: Int,
    val name: String,
    val dose: String,
    var taken: Boolean = false
)

@Composable
fun MedItem(medication: Medication, onUpdate: (Medication) -> Unit) {
    var isTaken by remember { mutableStateOf(medication.taken) }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(80.dp)
            .graphicsLayer {
                alpha = if (isTaken) 0.5f else 1f
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = medication.imageRes),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = medication.name,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        textDecoration = if (isTaken) TextDecoration.LineThrough else TextDecoration.None
                    )
                )
                Text(
                    text = medication.dose,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Gray,
                        textDecoration = if (isTaken) TextDecoration.LineThrough else TextDecoration.None
                    )
                )
            }
            Row {
                IconButton(onClick = {
                    isTaken = true
                    onUpdate(medication.copy(taken = true))
                }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Taken",
                        tint = Color.Green
                    )
                }
                IconButton(onClick = {
                    isTaken = false
                    onUpdate(medication.copy(taken = false))
                }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Not Taken",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}
