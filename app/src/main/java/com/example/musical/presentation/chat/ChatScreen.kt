package com.example.musical.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.musical.presentation.chat.components.MessageBubble

//@Composable
//fun ChatDialog(onDismiss: () -> Unit) {
//    var userInput by remember { mutableStateOf("") }
//    val chatMessages = remember { mutableStateListOf("How are you?") }
//
//    fun sendMessage(message: String) {
//        if (message.isNotBlank()) {
//            chatMessages.add(message)
//            chatMessages.add("I am a bot, I received your message: $message")
//            userInput = ""
//        }
//    }
//
//    Dialog(onDismissRequest = { onDismiss() }) {
//        Surface(
//            shape = RoundedCornerShape(16.dp),
//            color = Color.White,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Column(
//                modifier = Modifier
//                    .padding(16.dp)
//                    .background(Color.White),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = "Chat with Bot",
//                    style = MaterialTheme.typography.titleLarge,
//                    color = Color.Black
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                Column(
//                    modifier = Modifier
//                        .weight(1f)
//                        .fillMaxWidth()
//                        .padding(8.dp)
//                        .background(Color(0xFFF0F0F0))
//                        .clip(RoundedCornerShape(8.dp))
//                        .padding(8.dp)
//                ) {
//                    chatMessages.forEach { message ->
//                        Text(
//                            text = message,
//                            style = MaterialTheme.typography.bodyMedium,
//                            color = Color.Black,
//                            modifier = Modifier.padding(4.dp)
//                        )
//                    }
//                }
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 8.dp),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    OutlinedTextField(
//                        value = userInput,
//                        onValueChange = { userInput = it },
//                        placeholder = { Text(text = "Type your message...") },
//                        modifier = Modifier.weight(1f)
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Button(onClick = { sendMessage(userInput) }) {
//                        Text(text = "Send")
//                    }
//                }
//            }
//        }
//    }
//}

data class ChatMessage(val text: String, val isUser: Boolean)

@Composable
fun ChatScreen(modifier: Modifier = Modifier, navController: NavController) {
    val chatMessages = remember {
        mutableStateListOf(
            ChatMessage("Hello! How can I assist you today?", false)
        )
    }
    var userInput by remember { mutableStateOf("") }

    fun sendMessage(message: String) {
        if (message.isNotBlank()) {
            chatMessages.add(ChatMessage(message, true))
            // Simulate bot response
            chatMessages.add(ChatMessage("I am a bot, I received your message: $message", false))
            userInput = ""
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(Modifier.fillMaxWidth()) {
            OutlinedIconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = { navController.popBackStack() },
                shape = RoundedCornerShape(8.dp),
            ) {
                Icon(
                    modifier = Modifier.padding(start = 8.dp),
                    imageVector = Icons.Filled.ArrowBackIos,
                    contentDescription = "Back button"
                )
            }

            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "MediBot",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            modifier = Modifier
                .fillMaxSize()
//                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    chatMessages.forEach { message ->
                        MessageBubble(
                            message = message.text,
                            isSender = message.isUser
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = userInput,
                        onValueChange = { userInput = it },
                        placeholder = { Text(text = "Type your message...") },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { sendMessage(userInput) }) {
                        Text(text = "Send")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    MaterialTheme {
        ChatScreen(navController = rememberNavController())
    }
}