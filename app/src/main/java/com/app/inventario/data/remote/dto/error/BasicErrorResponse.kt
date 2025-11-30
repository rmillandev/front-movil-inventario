package com.app.inventario.data.remote.dto.error

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BasicErrorResponse(

    @SerialName("statusCode")
    val statusCode: Int,

    @SerialName("errorMessage")
    val errorMessage: String

) : ServerErrorBody {
    override val message: String get() = errorMessage
}

