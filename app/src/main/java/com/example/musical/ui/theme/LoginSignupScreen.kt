package com.example.musical.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginSignupScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to Medical App",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("doctorLogin") }) {
            Text(text = "Log in as Doctor")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { navController.navigate("patientLogin") }) {
            Text(text = "Log in as Patient")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { navController.navigate("signup") }) {
            Text(text = "Sign Up")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    var selectedRole by remember { mutableStateOf("Select Role") }
    val roles = listOf("Doctor", "Patient")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sign Up",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = selectedRole,
                onValueChange = { },
                readOnly = true,
                label = { Text("Role") },
                trailingIcon = {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.clickable { expanded = !expanded }
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                roles.forEach { role ->
                    DropdownMenuItem(
                        text = { Text(text = role) },
                        onClick = {
                            selectedRole = role
                            expanded = false
                            if (role == "Doctor") {
                                navController.navigate("doctorSignup")
                            } else {
                                navController.navigate("patientSignup")
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DoctorLoginScreen(navController: NavController) {
    LoginForm(
        title = "Doctor Login",
        onLogin = { username, password ->
            // Implement your login logic here
            if (username.isNotBlank() && password.isNotBlank()) {
                navController.navigate("doctorHome")
            }
        }
    )
}

@Composable
fun PatientLoginScreen(navController: NavController) {
    LoginForm(
        title = "Patient Login",
        onLogin = { username, password ->
            // Implement your login logic here
            if (username.isNotBlank() && password.isNotBlank()) {
                navController.navigate("patientHome")
            }
        }
    )
}

@Composable
fun DoctorSignupScreen(navController: NavController) {
    SignupForm(
        title = "Doctor Signup",
        onSignup = { username, email, password ->
            // Implement your signup logic here
            if (username.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
                navController.navigate("doctorHome")
            }
        }
    )
}

@Composable
fun PatientSignupScreen(navController: NavController) {
    SignupForm(
        title = "Patient Signup",
        onSignup = { username, email, password ->
            // Implement your signup logic here
            if (username.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
                navController.navigate("patientHome")
            }
        }
    )
}

@Composable
fun LoginForm(title: String, onLogin: (String, String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (username.isNotBlank() && password.isNotBlank()) {
                onLogin(username, password)
            } else {
                errorMessage = "Please enter both username and password"
            }
        }) {
            Text(text = "Login")
        }
        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = errorMessage, color = Color.Red)
        }
    }
}

@Composable
fun SignupForm(title: String, onSignup: (String, String, String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (username.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
                onSignup(username, email, password)
            } else {
                errorMessage = "Please enter all details"
            }
        }) {
            Text(text = "Sign Up")
        }
        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = errorMessage, color = Color.Red)
        }
    }
}
