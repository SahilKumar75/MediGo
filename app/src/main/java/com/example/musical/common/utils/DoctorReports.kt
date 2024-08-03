package com.example.musical.common.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun DoctorReport(navController: NavHostController) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding()
                .padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            item {
                Row(
                    modifier = Modifier
                ) {
                androidx.compose.material3.Text(
                    text = "Assigned Patient",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                       // .fillMaxWidth()
                        .padding(start = 0.dp, top = 16.dp)
                )
                     Row (modifier = Modifier.padding(20.dp,16.dp,10.dp,0.dp)){
                         Icon(
                             imageVector = Icons.Filled.Add,
                             contentDescription = "Create a Report",
                             tint = Color.White,
                             modifier = Modifier
                                 .size(24.dp)
                                 .background(Color(0xFF094999), CircleShape)
                                 .padding(4.dp)
                         )
                         Spacer(modifier = Modifier.width(8.dp))
                         Text(
                             text = "Create a Report",
                             style = MaterialTheme.typography.bodyMedium.copy(
                                 color = Color(0xFF094999)
                             ),
                             fontSize = 14.sp,
                             modifier = Modifier.clickable {
                                 navController.navigate("patientReportForm")
                             }
                         )
                     }
                }
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(500.dp)
                ) {
                    // Use LazyColumn inside ElevatedCard to avoid verticalScroll conflict
                    LazyColumn(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(getPatientlist(navController)) { patient ->
                            PatientDetail(
                                img = patient.img,
                                name = patient.name,
                                disease = patient.disease,
                                description = patient.description
                            )
                        }
                    }
                }
            }

            item {
                androidx.compose.material3.Text(
                    text = "Published Report",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 16.dp)
                )
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(200.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Add more content related to "Your Doc" here
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun doctorreport(){
    val navController= rememberNavController()
    DoctorReport(navController)
}
