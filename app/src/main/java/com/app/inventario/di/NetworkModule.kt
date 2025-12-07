package com.app.inventario.di

import com.app.inventario.data.local.datastore.UserDataStore
import com.app.inventario.data.remote.AuthInterceptor
import com.app.inventario.data.remote.api.IAuthApi
import com.app.inventario.data.remote.api.ICategoriaApi
import com.app.inventario.data.remote.api.IMovimientoInventarioApi
import com.app.inventario.data.remote.api.IProductoApi
import com.app.inventario.data.remote.api.IProveedorApi
import com.app.inventario.data.remote.api.IUsuarioApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://10.0.2.2:9095/api/"

    @Provides
    @Singleton
    fun provideOkHttp(authInterceptor: AuthInterceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(userDataStore: UserDataStore): AuthInterceptor = AuthInterceptor(userDataStore)

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): IAuthApi = retrofit.create(IAuthApi::class.java)

    @Provides
    @Singleton
    fun provideCategoriaApi(retrofit: Retrofit): ICategoriaApi = retrofit.create(ICategoriaApi::class.java)

    @Provides
    @Singleton
    fun provideProveedorApi(retrofit: Retrofit): IProveedorApi = retrofit.create(IProveedorApi::class.java)

    @Provides
    @Singleton
    fun provideProductoApi(retrofit: Retrofit): IProductoApi = retrofit.create(IProductoApi::class.java)

    @Provides
    @Singleton
    fun provideMovimientoInventarioApi(retrofit: Retrofit): IMovimientoInventarioApi = retrofit.create(IMovimientoInventarioApi::class.java)

    @Provides
    @Singleton
    fun provideUsuarioApi(retrofit: Retrofit): IUsuarioApi = retrofit.create(IUsuarioApi::class.java)

}