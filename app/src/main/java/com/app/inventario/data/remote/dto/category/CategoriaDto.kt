package com.app.inventario.data.remote.dto.category

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoriaDto(

    @SerialName("id")
    val id: Int? = null,

    @SerialName("nombre")
    val nombre: String,

    @SerialName("descripcion")
    val descripcion: String

)
