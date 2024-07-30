package com.example.musical.common.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                    // Add more content related to "Weekly Progress" here
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
                    // Add more content related to "Monthly Progress" here
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
                progress = (value - minValue).toFloat() / (maxValue - minValue),
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
