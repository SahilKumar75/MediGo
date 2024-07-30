package com.example.musical.presentation.chat

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.musical.R
import com.example.musical.presentation.chat.components.ChatItem
import java.io.File
import java.io.IOException

data class Message(val text: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(navController: NavController, chatItem: ChatItem) {
    var message by remember { mutableStateOf(TextFieldValue("")) }
    val messages = remember { mutableStateListOf<Message>() }
    var isRecording by remember { mutableStateOf(false) }
    var recorder: MediaRecorder? by remember { mutableStateOf(null) }

    val context = LocalContext.current
    val outputDir = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC)
    val outputFile = File(outputDir, "recorded_audio.3gp")

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission granted
        } else {
            // Permission denied
        }
    }

    fun startRecording() {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            recorder = MediaRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                setOutputFile(outputFile.absolutePath)
                try {
                    prepare()
                    start()
                    isRecording = true
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        } else {
            permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }
    }

    fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
        isRecording = false
        // Handle the recorded audio file (e.g., add to messages, upload, etc.)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(chatItem.name) },
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
                        Text(
                            text = message.text,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(
                        onClick = {
                            if (isRecording) {
                                stopRecording()
                            } else {
                                startRecording()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = if (isRecording) "Stop Recording" else "Record Audio",
                            tint = if (isRecording) Color.Red else Color.Black
                        )
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
                            messages.add(Message(message.text))
                            message = TextFieldValue("")
                        }
                    }) {
                        Text("Send")
                    }
                }
            }
        }
    )
}
