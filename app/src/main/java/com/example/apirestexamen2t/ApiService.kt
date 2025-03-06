package com.example.apirestexamen2t

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): ProductResponse


}
object RetrofitClient {
    private const val BASE_URL = "https://peticiones.online/api/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}