package com.example.musical.common.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.musical.R


@Preview(showSystemUi = true)
@Composable
fun PreviewRefer(patientViewModel: PatientViewModel = viewModel()) {
    val patients by patientViewModel.patients

    LazyColumn(content = {
        items(patients) { item ->
            PatientDetail1(
                img = item.img,
                name = item.name,
                disease = item.disease,
                description = item.description,
                onRemove = { patient ->
                    patientViewModel.removePatient(patient)
                }
            )
        }
    })
}

@Composable
fun PatientDetail1(
    img: Int,
    name: String,
    disease: String,
    description: String,
    onRemove: (Patient1) -> Unit
) {
    var dialogOpen by remember {
        mutableStateOf(false)
    }

    if (dialogOpen) {
        AlertDialog(onDismissRequest = {
            dialogOpen = false
        }, buttons = {
            Button(onClick = {
                onRemove(Patient1(img, name, disease, description))
                dialogOpen = false
            }) {
                Text(text = "Remove $name")
            }
        })
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(175.dp)
            .clickable {
                dialogOpen = true
            },
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(img),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .padding(8.dp)
            )
            ItemDescription1(name, disease, description)
        }
    }
}

@Composable
fun ItemDescription1(name: String, disease: String, description: String) {
    Column {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = disease,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = description,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .height(70.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier
                .padding(100.dp, 0.dp, 0.dp, 0.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Create a Refer",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .background(Color(0xFF094999), CircleShape)
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            androidx.compose.material.Text(
                text = "Create a Refer",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF094999)
                ),
                fontSize = 14.sp
            )
        }
    }
}

data class Patient1(
    val img: Int,
    val name: String,
    val disease: String,
    val description: String
)
val list= mutableListOf<Patient1>()
fun getPatientList1(): MutableList<Patient1> {
    return list
}

class PatientViewModel : ViewModel() {
    private val _patients = mutableStateOf(getPatientList1())
    val patients: State<MutableList<Patient1>> = _patients

    fun removePatient(patient: Patient1) {
        _patients.value = _patients.value.toMutableList().also {
            it.remove(patient)
        }
    }
}


