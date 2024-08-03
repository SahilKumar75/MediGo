package com.example.musical.common.utils


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TimelineItem(treatmentProgress: TreatmentProgress) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        color = if (treatmentProgress.completed) MaterialTheme.colorScheme.primary else Color.Gray,
                        shape = CircleShape
                    )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .height(60.dp)
                    .background(Color.Gray)
            )
        }
        Column {
            Text(
                text = treatmentProgress.title,
                style = MaterialTheme.typography.titleMedium.copy(color = if (treatmentProgress.completed) MaterialTheme.colorScheme.primary else Color.Gray)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = treatmentProgress.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
