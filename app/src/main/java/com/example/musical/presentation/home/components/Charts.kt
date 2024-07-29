package com.example.musical.presentation.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun BarGraph(data: List<Float>, modifier: Modifier = Modifier) {
    val maxValue = data.maxOrNull() ?: 0f
    val density = LocalDensity.current
    val spacing = with(density) { 15.dp.toPx() } // Define the spacing between bars

    Canvas(modifier = modifier) {
        val totalSpacing = spacing * (data.size - 1)
        val barWidth = (size.width - totalSpacing) / data.size

        data.forEachIndexed { index, value ->
            val barHeight = (value / maxValue) * size.height
            val barX = index * (barWidth + spacing)
            drawRect(
                color = Color(0xFF4894fe),
                topLeft = Offset(x = barX, y = size.height - barHeight),
                size = Size(width = barWidth, height = barHeight)
            )
        }
    }
}

@Composable
fun PieChart(data: List<Float>, modifier: Modifier = Modifier) {
    val totalValue = data.sum()
    val density = LocalDensity.current
    val spacing = with(density) { 2.dp.toPx() } // Define the spacing between segments in dp

    Canvas(modifier = modifier) {
        val diameter = size.minDimension
        val radius = diameter / 2
        val center = Offset(radius, radius)
        var startAngle = 0f

        data.forEach { value ->
            val sweepAngle = (value / totalValue) * 360f - spacing
            drawArc(
                color = Color(0xFF4894fe),
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = true,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(diameter, diameter)
            )
            startAngle += sweepAngle + spacing
        }
    }
}
