package com.example.musical.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.text.font.FontWeight
import com.example.musical.R
import com.example.musical.presentation.home.components.ImageCarousel
import com.example.musical.presentation.home.components.MedItem
import com.example.musical.presentation.home.components.Medication
import kotlin.math.abs

@Composable
fun HomeScreen(navController: NavController) {
    var offsetX by remember { mutableStateOf(0f) }
    val medications = remember {
        mutableStateListOf(
            Medication(R.drawable.med1, "Medicine 1", "1 tablet in the morning"),
            Medication(R.drawable.med2, "Medicine 2", "2 tablets after lunch"),
            Medication(R.drawable.med3, "Medicine 3", "1 tablet before bed")
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("chatRoute") },
                containerColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Filled.Chat,
                    contentDescription = "Chat"
                )
            }
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5))
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures { change, dragAmount ->
                            offsetX += dragAmount
                            if (offsetX < -300) { // Detect left to right swipe
                                navController.navigate("chatRoute")
                            }
                            if (abs(offsetX) > 10) {
                                change.consume()
                            }
                        }
                    }
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(top = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    item {
                        ImageCarousel()
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Your Meds Section (conditionally rendered)
                    if (medications.any { !it.taken }) {
                        item {
                            Text(
                                text = "Your Daily Meds",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, top = 16.dp)
                            )
                        }

                        items(medications.filter { !it.taken }) { medication ->
                            MedItem(medication) { updatedMed ->
                                medications.remove(medication)
                                medications.add(updatedMed)
                            }
                        }

                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }

                    // Taken Meds Section
                    if (medications.any { it.taken }) {
                        item {
                            Text(
                                text = "Taken Meds",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, top = 16.dp)
                            )
                        }

                        items(medications.filter { it.taken }) { medication ->
                            MedItem(medication) { updatedMed ->
                                medications.remove(medication)
                                medications.add(updatedMed)
                            }
                        }

                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }

                    // Your Doc Section
                    item {
                        Text(
                            text = "Your Doc",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, top = 16.dp)
                        )
                        ElevatedCard(
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .height(150.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                // Add more content related to "Your Doc" here
                            }
                        }
                    }
                }
            }
        }
    )
}
