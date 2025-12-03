package com.app.inventario.data.repository

import com.app.inventario.data.core.apiCall
import com.app.inventario.data.remote.api.ICategoriaApi
import com.app.inventario.data.remote.dto.category.CategoriaDto
import com.app.inventario.data.remote.dto.success.CreateResponseDto
import com.app.inventario.data.remote.dto.success.ReadResponseDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriaRepository @Inject constructor(
    private val api: ICategoriaApi
) {

    fun getAll(pageNumber: Int, pageSize: Int): Flow<Result<ReadResponseDto<CategoriaDto>>> {
        return apiCall {
            api.getAll(
                pageNumber = pageNumber,
                pageSize = pageSize
            )
        }
    }

    fun getById(id: Int): Flow<Result<CategoriaDto>> {
        return apiCall { api.getById(id) }
    }

    fun create(data: CategoriaDto): Flow<Result<CreateResponseDto<CategoriaDto>>> {
        return apiCall { api.create(data) }
    }

    fun update(id: Int, data: CategoriaDto): Flow<Result<Unit>> {
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