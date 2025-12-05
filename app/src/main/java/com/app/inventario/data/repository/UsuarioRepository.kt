package com.app.inventario.data.repository

import com.app.inventario.data.core.apiCall
import com.app.inventario.data.remote.api.IUsuarioApi
import com.app.inventario.data.remote.dto.success.CreateResponseDto
import com.app.inventario.data.remote.dto.success.ReadResponseDto
import com.app.inventario.data.remote.dto.usuario.CreateUsuarioDto
import com.app.inventario.data.remote.dto.usuario.ReadUsuarioDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsuarioRepository @Inject constructor(
    private val api: IUsuarioApi
) {

    fun getAll(
        pageNumber: Int? = null,
        pageSize: Int? = null
    ): Flow<Result<ReadResponseDto<ReadUsuarioDto>>> {
        return apiCall {
            api.getAll(
                pageNumber = pageNumber,
                pageSize = pageSize
            )
        }
    }

    fun create(data: CreateUsuarioDto): Flow<Result<CreateResponseDto<CreateUsuarioDto>>> {
        return apiCall { api.create(data) }
    }

    fun update(id: Int, data: CreateUsuarioDto): Flow<Result<Unit>> {
        return apiCall {
            api.update(
                id = id,
                data = data
            )
        }
    }

    fun delete(id: Int): Flow<Result<Unit>> {
        return apiCall { api.delete(id) }
    }

}