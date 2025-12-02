package com.app.inventario.data.remote

import com.app.inventario.data.local.datastore.UserDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val userDataStore: UserDataStore
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val token = runBlocking { userDataStore.token.first() }

        val request = if (token.isNotEmpty()) {
            chain.request().newBuilder()
                .addHeader(name = "Authorization", value = "Bearer $token")
                .build()
        } else {
            chain.request()
        }

        return chain.proceed(request)

    }

}