package com.example.musical.common.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.musical.R

@Composable
fun PatientDetail(img: Int, name: String, disease: String, description: String) {
   // val navController= rememberNavController()
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(200.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(img),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .padding(8.dp)
            )
            ItemDescription(name, disease, description)
        }
    }
}

@Composable
fun ItemDescription(name: String, disease: String, description: String) {
    val navController= rememberNavController()
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
    }
}

data class Patient(val img: Int, val name: String, val disease: String, val description: String)

fun getPatientlist(navController: NavController): MutableList<Patient> {
    val list = mutableListOf<Patient>()
    list.add(
        Patient(
            img = R.drawable.profile_image, name = "Programmer", disease = "Who learn language", description =
            "Heart disease, also known as cardiovascular disease, encompasses a range of conditions affecting the heart and blood vessels. It is a leading cause of death globally and can involve issues like coronary artery disease, heart rhythm problems (arrhythmias), and heart defects, among others."
        )
    )
    list.add(Patient(img = R.drawable.profile_image, name = "Programmer",disease = "Who learn language",description =
    "Heart disease, also known as cardiovascular disease, encompasses a range of conditions affecting the heart and blood vessels. It is a leading cause of death globally and can involve issues like coronary artery disease, heart rhythm problems (arrhythmias), and heart defects, among others."))
    list.add(Patient(img = R.drawable.profile_image, name = "Programmer",disease = "Who learn language",description =
    "Heart disease, also known as cardiovascular disease, encompasses a range of conditions affecting the heart and blood vessels. It is a leading cause of death globally and can involve issues like coronary artery disease, heart rhythm problems (arrhythmias), and heart defects, among others."))
    list.add(Patient(img = R.drawable.profile_image, name = "Programmer",disease = "Who learn language",description =
    "Heart disease, also known as cardiovascular disease, encompasses a range of conditions affecting the heart and blood vessels. It is a leading cause of death globally and can involve issues like coronary artery disease, heart rhythm problems (arrhythmias), and heart defects, among others."))
    list.add(Patient(img = R.drawable.profile_image, name = "Programmer",disease = "Who learn language",description =
    "Heart disease, also known as cardiovascular disease, encompasses a range of conditions affecting the heart and blood vessels. It is a leading cause of death globally and can involve issues like coronary artery disease, heart rhythm problems (arrhythmias), and heart defects, among others."))
    list.add(Patient(img = R.drawable.profile_image, name = "Programmer",disease = "Who learn language",description =
    "Heart disease, also known as cardiovascular disease, encompasses a range of conditions affecting the heart and blood vessels. It is a leading cause of death globally and can involve issues like coronary artery disease, heart rhythm problems (arrhythmias), and heart defects, among others."))
    list.add(Patient(img = R.drawable.profile_image, name = "Programmer",disease = "Who learn language",description =
    "Heart disease, also known as cardiovascular disease, encompasses a range of conditions affecting the heart and blood vessels. It is a leading cause of death globally and can involve issues like coronary artery disease, heart rhythm problems (arrhythmias), and heart defects, among others."))
    list.add(Patient(img = R.drawable.profile_image, name = "Programmer",disease = "Who learn language",description =
    "Heart disease, also known as cardiovascular disease, encompasses a range of conditions affecting the heart and blood vessels. It is a leading cause of death globally and can involve issues like coronary artery disease, heart rhythm problems (arrhythmias), and heart defects, among others."))
    list.add(Patient(img = R.drawable.profile_image, name = "Programmer",disease = "Who learn language",description =
    "Heart disease, also known as cardiovascular disease, encompasses a range of conditions affecting the heart and blood vessels. It is a leading cause of death globally and can involve issues like coronary artery disease, heart rhythm problems (arrhythmias), and heart defects, among others."))
    list.add(Patient(img = R.drawable.profile_image, name = "Programmer",disease = "Who learn language",description =
    "Heart disease, also known as cardiovascular disease, encompasses a range of conditions affecting the heart and blood vessels. It is a leading cause of death globally and can involve issues like coronary artery disease, heart rhythm problems (arrhythmias), and heart defects, among others."))
    list.add(Patient(img = R.drawable.profile_image, name = "Programmer",disease = "Who learn language",description =
    "Heart disease, also known as cardiovascular disease, encompasses a range of conditions affecting the heart and blood vessels. It is a leading cause of death globally and can involve issues like coronary artery disease, heart rhythm problems (arrhythmias), and heart defects, among others."))
    list.add(Patient(img = R.drawable.profile_image, name = "Programmer",disease = "Who learn language",description =
    "Heart disease, also known as cardiovascular disease, encompasses a range of conditions affecting the heart and blood vessels. It is a leading cause of death globally and can involve issues like coronary artery disease, heart rhythm problems (arrhythmias), and heart defects, among others."))

    // Add more patients as needed
    return list
}

