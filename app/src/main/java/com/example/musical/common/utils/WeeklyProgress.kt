package com.example.musical.common.utils


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.example.musical.R

@Composable
fun WeeklyProgress() {
    val weeklyData = listOf(
        "Mon" to 7000,
        "Tue" to 8000,
        "Wed" to 7500,
        "Thu" to 9000,
        "Fri" to 8500,
        "Sat" to 9500,
        "Sun" to 10000
    )

    val totalSteps = weeklyData.sumOf { it.second }
    val maxSteps = weeklyData.maxOf { it.second }
    val progress by animateFloatAsState(targetValue = totalSteps / (7 * 10000f), animationSpec = tween(durationMillis = 1000))

    val weeklyCalories = listOf(2100, 2300, 2200, 2400, 2300, 2500, 2600)
    val weeklyDistance = listOf(5.0, 6.0, 5.5, 6.5, 6.0, 7.0, 7.5)
    val weeklyActiveMinutes = listOf(30, 40, 35, 45, 40, 50, 55)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(modifier = Modifier
            .fillMaxWidth()
            .height(300.dp) // Adjusted size for better fit
            .padding(16.dp)) {
            val barWidth = size.width / (weeklyData.size * 2)
            val maxHeight = size.height

            weeklyData.forEachIndexed { index, (day, steps) ->
                val barHeight = (steps.toFloat() / maxSteps) * maxHeight
                drawRect(
                    color = Color.Green,
                    topLeft = androidx.compose.ui.geometry.Offset(
                        x = index * 2 * barWidth,
                        y = maxHeight - barHeight
                    ),
                    size = androidx.compose.ui.geometry.Size(barWidth, barHeight)
                )

                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        day,
                        (index * 2 * barWidth + barWidth / 2),
                        maxHeight + 20f,
                        android.graphics.Paint().apply {
                            color = android.graphics.Color.BLACK
                            textAlign = android.graphics.Paint.Align.CENTER
                            textSize = 30f
                        }
                    )
                }
            }
        }



        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Overall Progress", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Canvas(modifier = Modifier
            .size(200.dp) // Adjusted size for better fit
            .padding(16.dp)) {
            val canvasSize = size.minDimension
            val strokeWidth = 20f
            drawIntoCanvas { canvas ->
                val paint = Paint().apply {
                    color = Color.Gray
                    this.strokeWidth = strokeWidth
                    style = PaintingStyle.Stroke
                }

                // Draw background arc
                canvas.drawArc(
                    Rect(
                        left = canvasSize / 6,
                        top = canvasSize / 6,
                        right = 5 * canvasSize / 6,
                        bottom = 5 * canvasSize / 6
                    ),
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    paint = paint
                )

                paint.color = Color.Green
                // Draw progress arc
                canvas.drawArc(
                    Rect(
                        left = canvasSize / 6,
                        top = canvasSize / 6,
                        right = 5 * canvasSize / 6,
                        bottom = 5 * canvasSize / 6
                    ),
                    startAngle = -90f,
                    sweepAngle = 360 * progress,
                    useCenter = false,
                    paint = paint
                )
            }
        }
    }
}
