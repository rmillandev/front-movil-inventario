package com.app.inventario.data.repository

import com.app.inventario.data.core.apiCall
import com.app.inventario.data.remote.api.IMovimientoInventarioApi
import com.app.inventario.data.remote.dto.movimientoInventario.MovimientoInventarioDto
import com.app.inventario.data.remote.dto.success.CreateResponseDto
import com.app.inventario.data.remote.dto.success.ReadResponseDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovimientoInventarioRepository @Inject constructor(
    private val api: IMovimientoInventarioApi
) {

    fun getHistorialMovimientoInventario(
        pageNumber: Int? = null,
        pageSize: Int? = null
    ): Flow<Result<ReadResponseDto<MovimientoInventarioDto>>> {
        return apiCall {
            api.getHistorialMovimientoInventario(
                pageNumber = pageNumber,
                pageSize = pageSize
            )
        }
    }

    fun createMovimientoInventario(
        data: MovimientoInventarioDto
    ): Flow<Result<CreateResponseDto<MovimientoInventarioDto>>> {
        return apiCall { api.createMovimientoInventario(data) }
    }

}