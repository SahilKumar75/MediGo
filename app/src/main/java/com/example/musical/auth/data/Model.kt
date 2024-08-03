package com.example.musical.auth.data

import kotlinx.serialization.Serializable

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)
@Serializable
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