package com.example.musical.presentation.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.musical.R
import com.example.musical.presentation.chat.components.ChatItem
import com.example.musical.presentation.chat.components.ChatItemView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatHomeScreen(navController: NavController) {
    val chatItems = listOf(
        ChatItem(name = "John Doe", message = "Hey, how are you?", time = "12:45 PM", avatar = R.drawable.ic_avatar_placeholder),
        ChatItem(name = "Jane Smith", message = "Let's catch up later!", time = "11:30 AM", avatar = R.drawable.ic_avatar_placeholder),
        // Add more chat items here
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Chat with Patients",
                        fontWeight = FontWeight.Bold
                    )
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
                    .padding(padding),
                verticalArrangement = Arrangement.Top
            ) {
                items(chatItems) { chatItem ->
                    ChatItemView(navController = navController, chatItem = chatItem)
                }
            }
        }
    )
}
