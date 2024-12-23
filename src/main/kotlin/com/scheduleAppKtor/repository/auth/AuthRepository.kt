package com.scheduleAppKtor.repository.auth

import com.scheduleAppKtor.model.AuthResponse
import com.scheduleAppKtor.model.SignInParams
import com.scheduleAppKtor.model.SignUpParams
import com.scheduleAppKtor.util.Response

interface AuthRepository {
    suspend fun signUp(params: SignUpParams): Response<AuthResponse>
    suspend fun signIn(params: SignInParams): Response<AuthResponse>
}