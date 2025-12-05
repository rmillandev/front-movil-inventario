package com.app.inventario.data.remote.api

import com.app.inventario.data.remote.BaseRoute
import com.app.inventario.data.remote.dto.success.CreateResponseDto
import com.app.inventario.data.remote.dto.success.ReadResponseDto
import com.app.inventario.data.remote.dto.usuario.CreateUsuarioDto
import com.app.inventario.data.remote.dto.usuario.ReadUsuarioDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface IUsuarioApi {

    @GET(BaseRoute.USUARIO)
    suspend fun getAll(
        @Query("pageNumber") pageNumber: Int? = null,
        @Query("pageSize") pageSize: Int? = null
    ): Response<ReadResponseDto<ReadUsuarioDto>>

    @POST(BaseRoute.USUARIO)
    suspend fun create(
        @Body data: CreateUsuarioDto
    ): Response<CreateResponseDto<CreateUsuarioDto>>

    @PUT("${BaseRoute.USUARIO}/{id}")
    suspend fun update(
        @Path("id") id: Int,
        @Body data: CreateUsuarioDto
    ): Response<Unit>

    @DELETE("${BaseRoute.USUARIO}/{id}")
    suspend fun delete(
        @Path("id") id: Int
    ): Response<Unit>

}