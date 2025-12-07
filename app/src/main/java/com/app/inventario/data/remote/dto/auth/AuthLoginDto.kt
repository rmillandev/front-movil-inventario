package com.app.inventario.data.remote.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthLoginDto(

    val username: String,

    val password: String

)