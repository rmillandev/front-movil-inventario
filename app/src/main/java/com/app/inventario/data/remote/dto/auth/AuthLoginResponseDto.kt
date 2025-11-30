package com.app.inventario.data.remote.dto.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthLoginResponseDto(

    @SerialName("token")
    val token: String,

    @SerialName("username")
    val username: String,

    @SerialName("rol")
    val rol: String,

    @SerialName("email")
    val email: String

)
