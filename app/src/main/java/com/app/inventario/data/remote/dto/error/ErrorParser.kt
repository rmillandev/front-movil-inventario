package com.app.inventario.data.remote.dto.error

import kotlinx.serialization.json.Json


object ErrorParser {

    fun parseError(json: String): ServerErrorBody? {
        val jsonFormat = Json { ignoreUnknownKeys = true }

        return try {
            jsonFormat.decodeFromString<ValidationErrorResponse>(json)
        } catch (_: Exception) {
            try {
                jsonFormat.decodeFromString<BasicErrorResponse>(json)
            } catch (_: Exception) {
                null
            }
        }
    }
}
