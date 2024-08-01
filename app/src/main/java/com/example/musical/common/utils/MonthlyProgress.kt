//package com.example.musical.common.utils
//
//
//import androidx.compose.animation.core.animateFloatAsState
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.Canvas
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.geometry.Rect
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Paint
//import androidx.compose.ui.graphics.PaintingStyle
//import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
//import androidx.compose.ui.graphics.nativeCanvas
//import androidx.compose.ui.unit.dp
//import com.example.musical.R
//
//@Composable
//fun MonthlyProgress() {
//    val monthlyData = listOf(
//        "Week 1" to 45000,
//        "Week 2" to 50000,
//        "Week 3" to 52000,
//        "Week 4" to 48000
//    )
//
//    val totalSteps = monthlyData.sumOf { it.second }
//    val maxSteps = monthlyData.maxOf { it.second }
//    val progress by animateFloatAsState(targetValue = totalSteps / (4 * 70000f), animationSpec = tween(durationMillis = 1000))
//
//    val monthlyCalories = listOf(15000, 16000, 15500, 14000)
//    val monthlyDistance = listOf(35.0, 40.0, 38.0, 34.0)
//    val monthlyActiveMinutes = listOf(300, 350, 320, 280)
//
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Canvas(modifier = Modifier
//            .fillMaxWidth()
//            .height(300.dp) // Adjusted size for better fit
//            .padding(16.dp)) {
//            val maxY = size.height
//            val maxX = size.width
//            val spacing = size.width / (monthlyData.size - 1)
//
//            val yPositions = monthlyData.map { (week, steps) ->
//                (steps.toFloat() / maxSteps) * maxY
//            }
//
//            for (i in 0 until yPositions.size - 1) {
//                drawLine(
//                    color = Color.Blue,
//                    start = Offset(x = i * spacing, y = maxY - yPositions[i]),
//                    end = Offset(x = (i + 1) * spacing, y = maxY - yPositions[i + 1]),
//                    strokeWidth = 5f
//                )
//            }
//
//            monthlyData.forEachIndexed { index, (week, steps) ->
//                drawCircle(
//                    color = Color.Red,
//                    radius = 8f,
//                    center = Offset(x = index * spacing, y = maxY - yPositions[index])
//                )
//
//                drawContext.canvas.nativeCanvas.apply {
//                    drawText(
//                        week,
//                        (index * spacing),
//                        maxY + 20f,
//                        android.graphics.Paint().apply {
//                            color = android.graphics.Color.BLACK
//                            textAlign = android.graphics.Paint.Align.CENTER
//                            textSize = 30f
//                        }
//                    )
//                }
//            }
//        }
//
//
//
//        Spacer(modifier = Modifier.height(16.dp))
//        Text(text = "Overall Progress", style = MaterialTheme.typography.bodyLarge)
//        Spacer(modifier = Modifier.height(8.dp))
//        Canvas(modifier = Modifier
//            .size(200.dp) // Adjusted size for better fit
//            .padding(16.dp)) {
//            val canvasSize = size.minDimension
//            val strokeWidth = 20f
//            drawIntoCanvas { canvas ->
//                val paint = Paint().apply {
//                    color = Color.Gray
//                    this.strokeWidth = strokeWidth
//                    style = PaintingStyle.Stroke
//                }
//
//                // Draw background arc
//                canvas.drawArc(
//                    Rect(
//                        left = canvasSize / 6,
//                        top = canvasSize / 6,
//                        right = 5 * canvasSize / 6,
//                        bottom = 5 * canvasSize / 6
//                    ),
//                    startAngle = 0f,
//                    sweepAngle = 360f,
//                    useCenter = false,
//                    paint = paint
//                )
//
//                paint.color = Color.Blue
//                // Draw progress arc
//                canvas.drawArc(
//                    Rect(
//                        left = canvasSize / 6,
//                        top = canvasSize / 6,
//                        right = 5 * canvasSize / 6,
//                        bottom = 5 * canvasSize / 6
//                    ),
//                    startAngle = -90f,
//                    sweepAngle = 360 * progress,
//                    useCenter = false,
//                    paint = paint
//                )
//            }
//        }
//    }
//}
