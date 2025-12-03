package com.app.inventario.data.remote.dto.success

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReadResponseDto<T>(

    @SerialName("data")
    val data: List<T>? = null,

    @SerialName("pageNumber")
    val pageNumber: Int? = null,

    @SerialName("pageSize")
    val pageSize: Int? = null,

    @SerialName("totalRecords")
    val totalRecors: Int? = null,

    @SerialName("totalPages")
    val totalPages: Int? = null

)
