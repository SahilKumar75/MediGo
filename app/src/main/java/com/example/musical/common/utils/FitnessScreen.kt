package com.example.musical.common.utils

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
