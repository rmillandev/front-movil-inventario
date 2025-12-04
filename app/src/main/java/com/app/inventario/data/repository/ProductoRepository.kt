package com.app.inventario.data.repository

import com.app.inventario.data.core.apiCall
import com.app.inventario.data.remote.api.IProductoApi
import com.app.inventario.data.remote.dto.producto.ProductoDto
import com.app.inventario.data.remote.dto.success.CreateResponseDto
import com.app.inventario.data.remote.dto.success.ReadResponseDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductoRepository @Inject constructor(
    private val api: IProductoApi
) {

    fun getAll(pageNumber: Int? = null, pageSize: Int? = null): Flow<Result<ReadResponseDto<ProductoDto>>> {
        return apiCall {
            api.getAll(
                pageNumber = pageNumber,
                pageSize = pageSize
            )
        }
    }

    fun getStockBajo(pageNumber: Int? = null, pageSize: Int? = null): Flow<Result<ReadResponseDto<ProductoDto>>> {
        return apiCall {
            api.getStockBajo(
                pageNumber = pageNumber,
                pageSize = pageSize
            )
        }
    }

    fun create(data: ProductoDto): Flow<Result<CreateResponseDto<ProductoDto>>> {
        return apiCall { api.create(data) }
    }

    fun update(id: Int, data: ProductoDto): Flow<Result<Unit>> {
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