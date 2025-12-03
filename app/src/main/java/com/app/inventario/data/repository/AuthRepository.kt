package com.app.inventario.data.repository

import com.app.inventario.data.core.apiCall
import com.app.inventario.data.remote.api.IAuthApi
import com.app.inventario.data.remote.dto.auth.AuthLoginDto
import com.app.inventario.data.remote.dto.auth.AuthLoginResponseDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: IAuthApi
) {

    fun login(username: String, password: String): Flow<Result<AuthLoginResponseDto>> {
        return apiCall {
            api.login(AuthLoginDto(
                username = username,
                password = password
            ))
        }
    }

}