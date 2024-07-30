package com.example.musical.presentation.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.musical.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


data class ChatItem(val name: String, val message: String, val time: String, val avatar: Int)

@Composable
fun ChatItemView(chatItem: ChatItem, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = chatItem.avatar),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = chatItem.name, style = MaterialTheme.typography.titleMedium)
            Text(text = chatItem.message, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Text(text = chatItem.time, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}



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
                title = { Text("Chats") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.Chat, contentDescription = "Back")
                    }
                }
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
                    ChatItemView(chatItem = chatItem) {
                        // Handle chat item click
                    }
                }
            }
        }
    )
}
