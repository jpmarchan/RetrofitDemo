package com.fortatic.apps.retrofit

import retrofit2.Call
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

/**
 * Retrofit necesita al menos dos cosas para construir una API
 *  - Una Fabrica de convertidores para Serializar/Deserializar objetos
 *  - Un BASE_URL del Servicio Web
 */
private val retrofit = Builder()
    .addConverterFactory(GsonConverterFactory.create()) //El convertidor le dice a Retrofit que hacer con los datos que trae de la WS
    .baseUrl(BASE_URL)
    .build()

//Aquí vamos a definir los métodos abstractos.
interface ApiService {
    @GET("posts")
    fun getAllComments(): Call<List<Comment>>

    @GET("posts/{id}")
    fun getCommentById(@Path("id") id: Int): Call<Comment>

    @GET("users")
    fun getAllUsers(): Call<List<User>>
}

/**
 * El Object UserApi se encargará de instanciar un objeto Retrofit
 * (aplicando el patrón de diseño Singleton)
 */
object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}