package com.example.musical.auth.data

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/user/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Result<RegisterResponse>
}
