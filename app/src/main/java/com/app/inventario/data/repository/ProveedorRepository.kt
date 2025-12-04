package com.app.inventario.data.repository

import com.app.inventario.data.core.apiCall
import com.app.inventario.data.remote.api.IProveedorApi
import com.app.inventario.data.remote.dto.proveedor.ProveedorDto
import com.app.inventario.data.remote.dto.success.CreateResponseDto
import com.app.inventario.data.remote.dto.success.ReadResponseDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProveedorRepository @Inject constructor(
    private val api: IProveedorApi
) {

    fun getAll(pageNumber: Int? = null, pageSize: Int? = null): Flow<Result<ReadResponseDto<ProveedorDto>>> {
        return apiCall {
            api.getAll(
                pageNumber = pageNumber,
                pageSize = pageSize
            )
        }
    }

    fun getById(id: Int): Flow<Result<ProveedorDto>> {
        return apiCall { api.getById(id) }
    }

    fun create(data: ProveedorDto): Flow<Result<CreateResponseDto<ProveedorDto>>> {
        return apiCall { api.create(data) }
    }

    fun update(id: Int, data: ProveedorDto): Flow<Result<Unit>> {
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