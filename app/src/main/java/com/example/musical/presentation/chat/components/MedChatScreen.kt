package com.example.musical.presentation.chat.components

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedChatScreen(navController: NavController, chatId: Int) {
    var message by remember { mutableStateOf(TextFieldValue()) }
    val messages = remember { mutableStateListOf<String>() }
    val context = LocalContext.current

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            messages.add("Image: $uri")
        }
    }

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            messages.add("File: $uri")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat with Med Bot $chatId") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp),
                    reverseLayout = true
                ) {
                    items(messages) { msg ->
                        Text(
                            text = msg,
                            modifier = Modifier
                                .padding(8.dp)
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                                .padding(8.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BasicTextField(
                        value = message,
                        onValueChange = { message = it },
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                            .background(Color.Gray.copy(alpha = 0.1f))
                            .padding(8.dp)
                    )

                    IconButton(onClick = {
                        if (message.text.isNotBlank()) {
                            messages.add(message.text)
                            message = TextFieldValue()
                        }
                    }) {
                        Icon(Icons.Filled.Send, contentDescription = "Send")
                    }

                    IconButton(onClick = {
                        imagePickerLauncher.launch("image/*")
                    }) {
                        Icon(Icons.Filled.Image, contentDescription = "Send Image")
                    }

                    IconButton(onClick = {
                        filePickerLauncher.launch(arrayOf("*/*"))
                    }) {
                        Icon(Icons.Filled.AttachFile, contentDescription = "Send File")
                    }

                    IconButton(onClick = {
                        // Implement audio recording functionality here
                    }) {
                        Icon(Icons.Filled.Mic, contentDescription = "Send Audio")
                    }
                }
            }
        }
    )
}
