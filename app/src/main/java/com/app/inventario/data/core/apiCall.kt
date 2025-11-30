package com.app.inventario.data.core

import com.app.inventario.data.remote.dto.error.ErrorParser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException

fun <T> apiCall(call: suspend () -> Response<T>): Flow<Result<T>> = flow {
    try {

        val response = call()

        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                emit(Result.success(body))
            } else {
                emit(Result.failure(Exception("Respuesta vacía")))
            }
        } else {
            val errorJson = response.errorBody()?.string()
            val parsed = errorJson?.let { ErrorParser.parseError(it) }

            emit(Result.failure(Exception(parsed?.message ?: "Error HTTP ${response.code()}")))
        }

    } catch (e: IOException) {
        emit(Result.failure(Exception("No internet connection")))
    } catch (e: Exception) {
        emit(Result.failure(e))
    }
}