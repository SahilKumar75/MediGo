package com.example.musical.common.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Transgender
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.musical.R

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PatientReportForm(navController: NavHostController) {
    var profilePicture: Painter = painterResource(id = R.drawable.baseline_person_24)
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var contactNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var diseasename by remember { mutableStateOf("") }
    var diseasedescription by remember { mutableStateOf("") }

    TopAppBar( title = { Text("Report") },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF006eff)
        ))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding()
            .padding(16.dp,75.dp,16.dp,16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = profilePicture,
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .clickable { /* Handle image change */ }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
        ){
            PatientDetailField(
                value = name,
                onValueChange = { name = it },
                label = "Name",
                placeholder = "Enter patient's name",
                icon = Icons.Filled.Person,
                f = 1f
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start),
                horizontalArrangement = Arrangement.SpaceBetween ) {
                PatientDetailField(
                    value = age,
                    onValueChange = { age = it },
                    label = "Age",
                    placeholder = "Enter patient's age",
                    icon = Icons.Filled.CalendarToday,
                    f = 0.45f
                )
                Spacer(modifier = Modifier.height(16.dp))
                PatientDetailField(
                    value = gender,
                    onValueChange = { gender = it },
                    label = "Gender",
                    placeholder = "Enter patient's gender",
                    icon = Icons.Filled.Transgender,
                    f = .9f
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            PatientDetailField(
                value = contactNumber,
                onValueChange = { contactNumber = it },
                label = "Contact Number",
                placeholder = "Enter contact number",
                icon = Icons.Filled.Phone,
                f = 1f
            )
            Spacer(modifier = Modifier.height(16.dp))
            PatientDetailField(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                placeholder = "Enter email address",
                icon = Icons.Filled.Email,
                f = 1f
            )
            Spacer(modifier = Modifier.height(16.dp))
            PatientDetailField(
                value = diseasename,
                onValueChange = { diseasename = it },
                label = "Disease Name",
                placeholder = "Disease Name",
                icon = Icons.Filled.Add,
                f = 1f
            )
            Spacer(modifier = Modifier.height(16.dp))
            PatientDetailField(
                value = diseasedescription,
                onValueChange = { diseasedescription = it },
                label = "Description about the disease",
                placeholder = "Disease Description",
                icon = Icons.Filled.LibraryBooks,
                f = 1f
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* Handle form submission */ },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Submit",modifier=Modifier.clickable{
                    list.add(Patient1(name=name,img=R.drawable.profile_image, disease = diseasename, description = diseasedescription))
                }

                )
            }
        }
    }
}

@Composable
fun PatientDetailField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    icon: ImageVector,
    f : Float
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        modifier = Modifier.fillMaxWidth(f)
    )
}