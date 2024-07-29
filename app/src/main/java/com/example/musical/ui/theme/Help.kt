package com.example.musical.ui.theme

import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musical.R

data class Lib(@DrawableRes val icon: Int, val name: String, val phoneNumber: String?)

val libraries = listOf(
    Lib(R.drawable.baseline_add_call_24, "Call your Doc", "tel:1234567890"),
    Lib(R.drawable.baseline_message_24, "Message your Doc", null),
    Lib(R.drawable.baseline_directions_car_24, "Ambulance", "tel:0987654321"),
    Lib(R.drawable.baseline_medication_24, "Medicine", null),
    Lib(R.drawable.baseline_feedback_24, "Feedback", null)
)

@Composable
fun Help() {
    Card(
        modifier = Modifier.padding(16.dp),
        elevation = 8.dp,
    ) {
        LazyColumn {
            items(libraries) { lib ->
                LibItem(lib = lib)
            }
        }
    }
}

@Composable
fun LibItem(lib: Lib) {
    val context = LocalContext.current
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .clickable {
                    when (lib.name) {
                        "Message your Doc" -> {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:"))
                            intent.putExtra("sms_body", "Hello Doc")
                            context.startActivity(intent)
                        }
                        else -> {
                            lib.phoneNumber?.let { phoneNumber ->
                                val intent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber))
                                context.startActivity(intent)
                            }
                        }
                    }
                },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(
                    painter = painterResource(id = lib.icon),
                    modifier = Modifier.padding(horizontal = 8.dp),
                    contentDescription = lib.name
                )
                Text(text = lib.name)
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Arrow Right"
            )
        }
        Divider(color = Color.LightGray)
    }
}

@Preview
@Composable
fun PreviewHelp() {
    Help()
}
