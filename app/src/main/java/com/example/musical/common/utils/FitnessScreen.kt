package com.example.musical.common.utils

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
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
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), // Make the card height dynamic
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    StatItem(
                        imageRes = R.drawable.ic_spo2, // Replace with your SpO2 icon resource
                        label = "SpO2",
                        value = 95,
                        minValue = 90,
                        maxValue = 100
                    )
                    StatItem(
                        imageRes = R.drawable.ic_heartbeat, // Replace with your heartbeat icon resource
                        label = "Heartbeat",
                        value = 75,
                        minValue = 60,
                        maxValue = 100
                    )
                    StatItem(
                        imageRes = R.drawable.ic_steps, // Replace with your steps icon resource
                        label = "Steps",
                        value = 8000,
                        minValue = 0,
                        maxValue = 10000
                    )
                    StatItem(
                        imageRes = R.drawable.ic_calories, // Replace with your calories icon resource
                        label = "Calories Burned",
                        value = 500,
                        minValue = 0,
                        maxValue = 2000
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
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), // Make the card height dynamic
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
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
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), // Make the card height dynamic
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
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

    Column {
        weeklyData.forEach { (day, steps) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = day, style = MaterialTheme.typography.bodyLarge)
                val dayProgress by animateFloatAsState(targetValue = steps / 10000f, animationSpec = tween(durationMillis = 1000))
                LinearProgressIndicator(
                    progress = dayProgress,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                )
                Text(text = "$steps steps", style = MaterialTheme.typography.bodySmall)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Overall Progress", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Canvas(modifier = Modifier
            .fillMaxWidth()
            .height(150.dp) // Adjusted height for better fit
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
                        left = canvasSize / 4,
                        top = canvasSize / 4,
                        right = 3 * canvasSize / 4,
                        bottom = 3 * canvasSize / 4
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
                        left = canvasSize / 4,
                        top = canvasSize / 4,
                        right = 3 * canvasSize / 4,
                        bottom = 3 * canvasSize / 4
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

    Column {
        monthlyData.forEach { (week, steps) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = week, style = MaterialTheme.typography.bodyLarge)
                val weekProgress by animateFloatAsState(targetValue = steps / 70000f, animationSpec = tween(durationMillis = 1000))
                LinearProgressIndicator(
                    progress = weekProgress,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                )
                Text(text = "$steps steps", style = MaterialTheme.typography.bodySmall)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Overall Progress", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Canvas(modifier = Modifier
            .fillMaxWidth()
            .height(150.dp) // Adjusted height for better fit
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
                        left = canvasSize / 4,
                        top = canvasSize / 4,
                        right = 3 * canvasSize / 4,
                        bottom = 3 * canvasSize / 4
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
                        left = canvasSize / 4,
                        top = canvasSize / 4,
                        right = 3 * canvasSize / 4,
                        bottom = 3 * canvasSize / 4
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
