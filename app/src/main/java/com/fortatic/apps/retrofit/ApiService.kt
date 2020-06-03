package com.fortatic.apps.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

/*
 * Retrofit necesita al menos dos cosas para construir una API
 *  - Una Fabrica de convertidores para Serializar/Deserializar objetos
 *  - Un BASE_URL del Servicio Web
 */
private val retrofit = Builder()
    .addConverterFactory(GsonConverterFactory.create()) //El convertidor le dice a Retrofit que hacer con los datos que trae de la WS
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

/**
 * Definimos los métodos abstractos.
 *
 * Aquí cambiamos todas las llamadas Call<> por Deferred<> para retornar un Job.
 */
interface ApiService {

    @GET("posts")
    fun getAllPostsAsync(): Deferred<List<Post>>

    @GET("posts/{id}")
    fun getPostByIdAsync(@Path("id") id: Int): Deferred<Post>

    @GET("users")
    fun getAllUsersAsync(): Deferred<List<User>>
}

//El Object UserApi se encargará de instanciar un objeto Retrofit (aplicando el patrón de diseño Singleton)
object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}