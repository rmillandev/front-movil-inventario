package com.app.inventario.data.remote.dto.error

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ValidationErrorItem(

    @SerialName("Field")
    val field: String,

    @SerialName("Message")
    val errorMessage: String

)

