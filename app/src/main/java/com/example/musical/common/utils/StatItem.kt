package com.example.musical.common.utils


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musical.R

@Composable
fun StatItem(
    imageRes: Int,
    label: String,
    value: Int,
    minValue: Int,
    maxValue: Int
) {
    val progress by animateFloatAsState(
        targetValue = (value - minValue).toFloat() / (maxValue - minValue),
        animationSpec = tween(durationMillis = 1000)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = label,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = label, style = MaterialTheme.typography.bodyLarge)
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "$minValue", style = MaterialTheme.typography.bodySmall)
                Text(text = "$value", style = MaterialTheme.typography.bodySmall, color = Color.Green)
                Text(text = "$maxValue", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
