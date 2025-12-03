package com.app.inventario.data.remote.dto.proveedor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProveedorDto(

    @SerialName("nit")
    val nit: String? = null,

    @SerialName("nombre")
    val nombre: String? = null,

    @SerialName("telefono")
    val telefono: String? = null,

    @SerialName("email")
    val email: String? = null,

    @SerialName("direccion")
    val direccion: String? = null

)
