package com.app.inventario.data.remote.dto.producto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductoDto(

    @SerialName("codigo")
    val codigo: Int? = null,

    @SerialName("nombre")
    val nombre: String? = null,

    @SerialName("descripcion")
    val descripcion: String? = null,

    @SerialName("precio")
    val precio: Double? = null,

    @SerialName("stockActual")
    val stockActual: Int? = null,

    @SerialName("categoria")
    val categoria: String? = null,

    @SerialName("proveedor")
    val proveedor: String? = null,

    @SerialName("StockBajo")
    val stockBajo: String? = null

)
