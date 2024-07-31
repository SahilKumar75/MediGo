package com.example.musical.common.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormsScreen() {
    val formsList = listOf(
        "Radiology Requisition Form",
        "Vital Signs Flow Sheet",
        "Pain Assessment Flow Sheet",
        "Informed Consent for Refusal of Procedure",
        "Consent for Leave Against Medical Advice",
        "Consent for Outside Referral",
        "Informed Consent for Surgery & Anesthesia",
        "Blood Requisition Form",
        "Informed Consent for Management of High Risk Patient",
        "Operation Notes"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(formsList) { form ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        // Handle form item click
                    },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = form, style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}