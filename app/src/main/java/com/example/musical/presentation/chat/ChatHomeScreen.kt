package com.example.musical.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.musical.R
import com.example.musical.presentation.chat.components.ChatItem
import com.example.musical.presentation.chat.components.ChatItemView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatHomeScreen(navController: NavController) {
    val chatItems = listOf(
        ChatItem(name = "John Doe", message = "Hey, how are you?", time = "12:45 PM", avatar = R.drawable.ic_avatar_placeholder),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Chat with Patients",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        content = { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFFF0F0F0)), // Light background for the entire screen
                verticalArrangement = Arrangement.Top
            ) {
                items(chatItems) { chatItem ->
                    ChatItemView(navController = navController, chatItem = chatItem)
                    Divider(color = Color.LightGray, thickness = 1.dp) // Divider between chat items
                }
            }
        }
    )
}
