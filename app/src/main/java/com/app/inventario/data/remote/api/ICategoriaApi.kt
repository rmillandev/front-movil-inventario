package com.app.inventario.data.remote.api

import com.app.inventario.data.remote.BaseRoute
import com.app.inventario.data.remote.dto.category.CategoriaDto
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

interface ICategoriaApi {

    @GET(BaseRoute.CATEGORIA)
    suspend fun getAll(
        @Query("pageNumber") pageNumber: Int? = null,
        @Query("pageSize") pageSize: Int? = null
    ): Response<ReadResponseDto<CategoriaDto>>

    @GET("${BaseRoute.CATEGORIA}/{id}")
    suspend fun getById(
        @Path("id") id: Int
    ): Response<CategoriaDto>

    @POST(BaseRoute.CATEGORIA)
    suspend fun create(
        @Body data: CategoriaDto
    ): Response<CreateResponseDto<CategoriaDto>>

    @PUT("${BaseRoute.CATEGORIA}/{id}")
    suspend fun update(
        @Path("id") id: Int,
        @Body data: CategoriaDto
    ): Response<Unit>

    @DELETE("${BaseRoute.CATEGORIA}/{id}")
    suspend fun delete(
        @Path("id") id: Int
    ): Response<Unit>

}