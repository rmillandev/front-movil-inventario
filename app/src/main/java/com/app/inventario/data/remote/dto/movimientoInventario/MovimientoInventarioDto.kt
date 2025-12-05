package com.app.inventario.data.remote.dto.movimientoInventario

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovimientoInventarioDto(

    @SerialName("fecha")
    val fecha: String? = null,

    @SerialName("tipoMovimiento")
    val tipoMovimiento: String? = null,

    @SerialName("producto")
    val producto: String? = null,

    @SerialName("cantidad")
    val cantidad: Int? = null,

    @SerialName("responsableMovimiento")
    val responsableMovimiento: String? = null

)
