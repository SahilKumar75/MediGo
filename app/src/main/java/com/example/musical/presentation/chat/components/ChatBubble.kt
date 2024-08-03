package com.example.musical.presentation.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.max

@Composable
fun MessageBubble(
    message: String,
    isSender: Boolean
) {

    val radius =
        if (isSender) RoundedCornerShape(
            topStart = 16.dp,
            bottomStart = 16.dp,
            topEnd = 0.dp,
            bottomEnd = 16.dp
        ) else RoundedCornerShape(
            topStart = 0.dp,
            bottomStart = 16.dp,
            topEnd = 16.dp,
            bottomEnd = 16.dp
        )

    Row(
        modifier = Modifier
            .padding(bottom = 24.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .background(
                    color = if (isSender)
                        Color(0XFF1EBEA5)
                    else
                        Color(0XFF273443),
                    shape = radius
                )
                .padding(12.dp)
        ) {
            Text(
                text = message,
                color = Color.White,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.wrapContentWidth()
            )
        }
    }
}
