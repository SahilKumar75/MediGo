package com.example.musical.presentation.chat.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName

data class ChatItem(
    val name: String,
    val message: String,
    val time: String,
    @DrawableRes val avatar: Int
)


@Composable
fun ChatItemView(navController: NavController, chatItem: ChatItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                val chatItemJson = Gson().toJson(chatItem)
                navController.navigate("chat_screen/$chatItemJson")
            },
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