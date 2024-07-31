package com.example.musical.presentation.chat.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedChatHomeScreen(navController: NavController) {
    val chatItems = remember {
        listOf(
            MedChatItem(id = 1, name = "Chat 1", lastMessage = "Hello! How are you?", timestamp = "10:00 AM"),
            MedChatItem(id = 2, name = "Chat 2", lastMessage = "See you soon!", timestamp = "Yesterday")
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chats") }
            )
        },
        content = { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(chatItems) { chatItem ->
                    ChatItemRow(navController, chatItem)
                }
            }
        }
    )
}

@Composable
fun ChatItemRow(navController: NavController, chatItem: MedChatItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("medChatScreen/${chatItem.id}")
            }
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = chatItem.name, fontWeight = FontWeight.Bold)
            Text(text = chatItem.lastMessage)
        }
        Text(text = chatItem.timestamp)
    }
}
