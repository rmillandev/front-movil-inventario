package com.app.inventario.data.remote.api

import com.app.inventario.data.remote.BaseRoute
import com.app.inventario.data.remote.dto.movimientoInventario.MovimientoInventarioDto
import com.app.inventario.data.remote.dto.success.CreateResponseDto
import com.app.inventario.data.remote.dto.success.ReadResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IMovimientoInventarioApi {

    @GET(BaseRoute.MOVIMIENTO_INVENTARIO)
    suspend fun getHistorialMovimientoInventario(
        @Query("pageNumber") pageNumber: Int? = null,
        @Query("pageSize") pageSize: Int? = null
    ): Response<ReadResponseDto<MovimientoInventarioDto>>

    @POST(BaseRoute.MOVIMIENTO_INVENTARIO)
    suspend fun createMovimientoInventario(
        @Body data: MovimientoInventarioDto
    ): Response<CreateResponseDto<MovimientoInventarioDto>>

}