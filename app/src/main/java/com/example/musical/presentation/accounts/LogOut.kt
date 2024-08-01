package com.example.musical.presentation.accounts

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun LogOut(context: Context){
    Column(
        modifier = Modifier.height(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Log Out",
            modifier = Modifier.clickable {
                // Handle the log out action
                (context as? Activity)?.finish()
            }
        )
    }
}
@Composable
fun LogOut() {
    val context = LocalContext.current
    com.example.musical.presentation.accounts.LogOut(context = context)
}
