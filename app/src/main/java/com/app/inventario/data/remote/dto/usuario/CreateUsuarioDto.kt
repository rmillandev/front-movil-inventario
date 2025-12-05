package com.app.inventario.data.remote.dto.usuario

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUsuarioDto(

    @SerialName("email")
    val email: String? = null,

    @SerialName("userName")
    val userName: String? = null,

    @SerialName("password")
    val password: String? = null,

    @SerialName("rol")
    val rol: String? = null,

)
