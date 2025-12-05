package com.app.inventario.data.remote.dto.usuario

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReadUsuarioDto(

    @SerialName("id")
    val id: Int? = null,

    @SerialName("userName")
    val userName: String? = null,

    @SerialName("email")
    val email: String? = null,

    @SerialName("rol")
    val rol: String? = null,

)
