package com.example.musical.common.utils

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun PatientDiagnoseScreen(
    navController: NavController,
    medicineViewModel: MedicineViewModel = viewModel()
) {
    var medName by remember { mutableStateOf("") }
    var dose by remember { mutableStateOf("") }
    var days by remember { mutableStateOf("") }
    var sessionStatus by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Patient Diagnose Screen",
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = medName,
                onValueChange = { medName = it },
                label = { Text("Medicine Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = dose,
                onValueChange = { dose = it },
                label = { Text("Dosage") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = days,
                onValueChange = { days = it },
                label = { Text("Number of Days") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Button(
                    onClick = {
                        if (medName.isNotEmpty() && dose.isNotEmpty() && days.isNotEmpty()) {
                            medicineViewModel.prescribedMedicines.add(
                                PrescribedMedicine(
                                    name = medName,
                                    dosage = "$dose for $days days"
                                )
                            )
                            sessionStatus = "Completed"
                            // Clear input fields
                            medName = ""
                            dose = ""
                            days = ""
                        } else {
                            sessionStatus = "Please fill all fields"
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Complete Session")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        sessionStatus = "Cancelled"
                        // Handle session cancellation logic here
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancel Session")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (sessionStatus.isNotEmpty()) {
                Text(
                    text = "Session Status: $sessionStatus",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (medicineViewModel.prescribedMedicines.isNotEmpty()) {
                Text(
                    text = "Prescribed Medicines:",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                medicineViewModel.prescribedMedicines.forEach { medicine ->
                    Text(
                        text = "${medicine.name}: ${medicine.dosage}",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}
