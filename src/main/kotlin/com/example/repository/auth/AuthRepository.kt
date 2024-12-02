package com.example.repository.auth

import com.example.model.AuthResponse
import com.example.model.SignInParams
import com.example.model.SignUpParams
import com.example.util.Response

interface AuthRepository {
    suspend fun signUp(params: SignUpParams): Response<AuthResponse>
    suspend fun signIn(params: SignInParams): Response<AuthResponse>
}