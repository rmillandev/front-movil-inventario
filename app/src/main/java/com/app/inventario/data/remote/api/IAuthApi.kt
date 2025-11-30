package com.app.inventario.data.remote.api

import com.app.inventario.data.remote.dto.auth.AuthLoginDto
import com.app.inventario.data.remote.dto.auth.AuthLoginResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IAuthApi {

    @POST("auth/login")
    suspend fun login(
        @Body body: AuthLoginDto
    ): Response<AuthLoginResponseDto>

}