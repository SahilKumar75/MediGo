package com.example.musical.presentation.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun BarGraph(data: List<Float>, modifier: Modifier = Modifier) {
    val maxValue = data.maxOrNull() ?: 0f
    Canvas(modifier = modifier) {
        val barWidth = size.width / data.size
        data.forEachIndexed { index, value ->
            val barHeight = (value / maxValue) * size.height
            drawRect(
                color = Color(0xFF4894fe),
                topLeft = Offset(x = index * barWidth, y = size.height - barHeight),
                size = Size(width = barWidth, height = barHeight)
            )
        }
    }
}

@Composable
fun PieChart(data: List<Float>, modifier: Modifier = Modifier) {
    val totalValue = data.sum()
    var startAngle = 0f
    Canvas(modifier = modifier) {
        data.forEach { value ->
            val sweepAngle = (value / totalValue) * 360f
            drawArc(
                color = Color(0xFF4894fe),
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = true,
                topLeft = Offset.Zero,
                size = size
            )
            startAngle += sweepAngle
        }
    }
}