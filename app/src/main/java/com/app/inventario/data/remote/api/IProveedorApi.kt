package com.app.inventario.data.remote.api

import com.app.inventario.data.remote.BaseRoute
import com.app.inventario.data.remote.dto.proveedor.ProveedorDto
import com.app.inventario.data.remote.dto.success.CreateResponseDto
import com.app.inventario.data.remote.dto.success.ReadResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface IProveedorApi {

    @GET(BaseRoute.PROVEEDOR)
    suspend fun getAll(
        @Query("pageNumber") pageNumber: Int? = null,
        @Query("pageSize") pageSize: Int? = null
    ): Response<ReadResponseDto<ProveedorDto>>

    @GET("${BaseRoute.PROVEEDOR}/{id}")
    suspend fun getById(
        @Path("id") id: Int
    ): Response<ProveedorDto>

    @POST(BaseRoute.PROVEEDOR)
    suspend fun create(
        @Body data: ProveedorDto
    ): Response<CreateResponseDto<ProveedorDto>>

    @PUT("${BaseRoute.PROVEEDOR}/{id}")
    suspend fun update(
        @Path("id") id: Int,
        @Body data: ProveedorDto
    ): Response<Unit>

    @DELETE("${BaseRoute.PROVEEDOR}/{id}")
    suspend fun delete(
        @Path("id") id: Int
    ): Response<Unit>

}