package com.app.inventario.data.remote.api

import com.app.inventario.data.remote.BaseRoute
import com.app.inventario.data.remote.dto.producto.ProductoDto
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

interface IProductoApi {

    @GET("${BaseRoute.PRODUCTO}/{id}")
    suspend fun getAll(
        @Query("pageNumber") pageNumber: Int? = null,
        @Query("pageSize") pageSize: Int? = null
    ): Response<ReadResponseDto<ProductoDto>>

    @GET("${BaseRoute.PRODUCTO}/stock-bajo")
    suspend fun getStockBajo(
        @Query("pageNumber") pageNumber: Int? = null,
        @Query("pageSize") pageSize: Int? = null
    ): Response<ReadResponseDto<ProductoDto>>

    @POST(BaseRoute.PRODUCTO)
    suspend fun create(
        @Body data: ProductoDto
    ): Response<CreateResponseDto<ProductoDto>>

    @PUT("${BaseRoute.PRODUCTO}/{id}")
    suspend fun update(
        @Path("id") id: Int,
        @Body data: ProductoDto
    ): Response<Unit>

    @DELETE("${BaseRoute.PRODUCTO}/{id}")
    suspend fun delete(
        @Path("id") id: Int
    ): Response<Unit>

}