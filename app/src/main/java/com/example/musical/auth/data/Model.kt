package com.example.musical.auth.data

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val userId: String,
    val token: String
)


data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)
data class RegisterResponse(
    val token: String="",
    val errorMessage: String? = null,
    val user: User?=null
)
data class User(
    val id: Int,
    val username: String,
    val email: String,
)