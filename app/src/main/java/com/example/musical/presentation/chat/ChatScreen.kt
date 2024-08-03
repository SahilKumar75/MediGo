package com.example.musical.presentation.chat

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.musical.presentation.chat.components.MessageBubble
import com.google.gson.Gson
import java.util.Locale

data class Message(val text: String, val imageUri: Uri? = null, val fileUri: Uri? = null, val isUser: Boolean = false)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(navController: NavController) {
    var message by remember { mutableStateOf(TextFieldValue("")) }
    val messages = remember { mutableStateListOf<Message>() }
    val context = LocalContext.current

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
        } else {
        }
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            messages.add(Message(text = "", imageUri = it, isUser = true))
        }
    }

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            messages.add(Message(text = "", fileUri = it, isUser = true))
        }
    }

    val speechToTextLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val speechText = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (!speechText.isNullOrEmpty()) {
                messages.add(Message(text = speechText[0], isUser = true))
            }
        }
    }

    fun startSpeechToText() {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now...")
            }
            speechToTextLauncher.launch(intent)
        } else {
            permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("App Bot") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
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
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(messages) { message ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalAlignment = if (message.isUser) Alignment.End else Alignment.Start
                        ) {
                            if (message.text.isNotEmpty()) {
                                MessageBubble(message = message.text, isSender = message.isUser)
                            }
                            message.imageUri?.let {
                                Image(
                                    painter = rememberImagePainter(it),
                                    contentDescription = null,
                                    modifier = Modifier.size(200.dp)
                                )
                            }
                            message.fileUri?.let {
                                Text(text = "File: ${it.lastPathSegment}", color = Color.Blue)
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    IconButton(onClick = { startSpeechToText() }) {
                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = "Start Speech to Text",
                            tint = Color.Black
                        )
                    }
                    IconButton(onClick = { imagePickerLauncher.launch("image/*") }) {
                        Icon(imageVector = Icons.Default.Image, contentDescription = "Pick Image")
                    }
                    IconButton(onClick = { filePickerLauncher.launch("*/*") }) {
                        Icon(imageVector = Icons.Default.AttachFile, contentDescription = "Pick File")
                    }
                    BasicTextField(
                        value = message,
                        onValueChange = { message = it },
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                            .background(Color.Gray.copy(alpha = 0.2f), MaterialTheme.shapes.small)
                            .padding(8.dp),
                        decorationBox = { innerTextField ->
                            if (message.text.isEmpty()) {
                                Text(text = "Type a message", color = Color.Gray)
                            }
                            innerTextField()
                        }
                    )
                    Button(onClick = {
                        if (message.text.isNotEmpty()) {
                            messages.add(Message(text = message.text, isUser = true))
                            message = TextFieldValue("")
                            // Simulate bot response for demonstration
                        }
                    }) {
                        Text("Send")
                    }
                }
            }
        }
    )
}
