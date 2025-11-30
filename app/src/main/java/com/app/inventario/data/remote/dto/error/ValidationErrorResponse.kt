package com.app.inventario.data.remote.dto.error

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ValidationErrorResponse(

    @SerialName("statusCode")
    val statusCode: Int,

    @SerialName("validationErrorsMessage")
    val errors: List<ValidationErrorItem>

) : ServerErrorBody {

    override val message: String
        get() = errors.joinToString("\n") { "${it.field}: ${it.errorMessage}" }
}