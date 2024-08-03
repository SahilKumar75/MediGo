package com.example.musical.common.utils

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionUpdateScreen(
    navController: NavController,
    medicineViewModel: MedicineViewModel = viewModel()
) {
    val treatmentProgressList = listOf(
        TreatmentProgress("Consultation", "Initial consultation with the doctor", true),
        TreatmentProgress("Diagnostics", "Blood tests and scans", true),
        TreatmentProgress("Treatment Plan", "Personalized treatment plan creation", false),
        TreatmentProgress("Follow-up", "Regular follow-up sessions", false)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Session Update") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Treatment Progress",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            treatmentProgressList.forEach { treatmentProgress ->
                TimelineItem(treatmentProgress = treatmentProgress)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Prescribed Medicines",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            medicineViewModel.prescribedMedicines.forEach { prescribedMedicine ->
                MedicineItem(prescribedMedicine = prescribedMedicine)
            }
        }
    }
}
