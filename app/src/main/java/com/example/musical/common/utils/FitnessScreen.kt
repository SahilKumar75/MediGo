package com.example.musical.common.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.musical.R

@Composable
fun FitnessScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Your Stats Section
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Your Stats",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    StatItem(
                        imageRes = R.drawable.ic_spo2,
                        label = "SpO2",
                        value = 95,
                        minValue = 90,
                        maxValue = 100,
                        color = Color.Cyan
                    )
                    StatItem(
                        imageRes = R.drawable.ic_heartbeat,
                        label = "Heartbeat",
                        value = 75,
                        minValue = 60,
                        maxValue = 100,
                        color = Color.Red
                    )
                    StatItem(
                        imageRes = R.drawable.ic_steps,
                        label = "Steps",
                        value = 8000,
                        minValue = 0,
                        maxValue = 10000,
                        color = Color.Green
                    )
                    StatItem(
                        imageRes = R.drawable.ic_calories,
                        label = "Calories Burned",
                        value = 500,
                        minValue = 0,
                        maxValue = 2000,
                        color = Color(0xFFFFA500) // Orange
                    )
                }
            }
        }

        // Weekly Progress Section
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Weekly Progress",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    WeeklyProgress()
                }
            }
        }

        // Monthly Progress Section
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Monthly Progress",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    MonthlyProgress()
                }
            }
        }
    }
}

@Composable
fun StatItem(imageRes: Int, label: String, value: Int, minValue: Int, maxValue: Int, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = imageRes),
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = label, color = Color.White, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "$value", color = color, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            LinearProgressIndicator(
                progress = (value - minValue) / (maxValue - minValue).toFloat(),
                color = color,
                trackColor = Color.Gray,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

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
            .height(300.dp)
            .padding(16.dp)) {
            val barWidth = size.width / (weeklyData.size * 2)
            val maxHeight = size.height

            weeklyData.forEachIndexed { index, (day, steps) ->
                val barHeight = (steps.toFloat() / maxSteps) * maxHeight
                drawRect(
                    color = Color.Green,
                    topLeft = Offset(
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
                            color = android.graphics.Color.WHITE
                            textAlign = android.graphics.Paint.Align.CENTER
                            textSize = 36f // Increased text size
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Overall Progress", style = MaterialTheme.typography.headlineSmall, color = Color.White) // Increased text size
        Spacer(modifier = Modifier.height(8.dp))
        Canvas(modifier = Modifier
            .size(200.dp)
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


@Composable
fun MonthlyProgress() {
    val monthlyData = listOf(
        "Week 1" to 45000,
        "Week 2" to 50000,
        "Week 3" to 52000,
        "Week 4" to 48000
    )

    val totalSteps = monthlyData.sumOf { it.second }
    val maxSteps = monthlyData.maxOf { it.second }
    val progress by animateFloatAsState(targetValue = totalSteps / (4 * 70000f), animationSpec = tween(durationMillis = 1000))

    val monthlyCalories = listOf(15000, 16000, 15500, 14000)
    val monthlyDistance = listOf(35.0, 40.0, 38.0, 34.0)
    val monthlyActiveMinutes = listOf(300, 350, 320, 280)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(16.dp)) {
            val maxY = size.height
            val maxX = size.width
            val spacing = size.width / (monthlyData.size - 1)

            val yPositions = monthlyData.map { (week, steps) ->
                (steps.toFloat() / maxSteps) * maxY
            }

            for (i in 0 until yPositions.size - 1) {
                drawLine(
                    color = Color.Blue,
                    start = Offset(x = i * spacing, y = maxY - yPositions[i]),
                    end = Offset(x = (i + 1) * spacing, y = maxY - yPositions[i + 1]),
                    strokeWidth = 15f
                )
            }

            monthlyData.forEachIndexed { index, (week, steps) ->
                drawCircle(
                    color = Color.Red,
                    radius = 8f,
                    center = Offset(x = index * spacing, y = maxY - yPositions[index])
                )

                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        week,
                        (index * spacing),
                        maxY + 20f,
                        android.graphics.Paint().apply {
                            color = android.graphics.Color.WHITE
                            textAlign = android.graphics.Paint.Align.CENTER
                            textSize = 36f // Increased text size
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Overall Progress", style = MaterialTheme.typography.headlineSmall, color = Color.White) // Increased text style
        Spacer(modifier = Modifier.height(8.dp))
        Canvas(modifier = Modifier
            .size(200.dp)
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

                paint.color = Color.Blue
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

