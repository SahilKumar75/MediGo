package com.example.musical.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.example.musical.presentation.home.components.ImageCarousel
import kotlin.math.abs

@Composable
fun HomeScreen(navController: NavController) {
    var offsetX by remember { mutableStateOf(0f) }

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

                    // Your Meds Section
                    item {
                        Text(
                            text = "Your Meds",
                            style = MaterialTheme.typography.titleLarge,
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
                                // Add more content related to "Your Meds" here
                            }
                        }
                    }

                    // Your Doc Section
                    item {
                        Text(
                            text = "Your Doc",
                            style = MaterialTheme.typography.titleLarge,
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
