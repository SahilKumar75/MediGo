package com.example.musical.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.musical.R
import com.example.musical.common.utils.Doctor
import com.example.musical.common.utils.DoctorDetailsCard

@Composable
fun DocHomeScreen(navController: NavController) {
    val doctor = Doctor(
        imageRes = R.drawable.doc_image, // Replace with your doctor image resource
        name = "Dr. John Doe",
        specialty = "Cardiologist",
        contact = "+1 234 567 890",
        availability = "Mon - Fri, 9 AM - 5 PM"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        Text(
            text = "Doctor Home",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        DoctorDetailsCard(doctor)
    }
}
