package com.d3if3010.miniproject3.network

import com.d3if3010.miniproject3.model.Makanan
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private const val BASE_URL = "https://wjhxmplq-3000.asse.devtunnels.ms/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MakananApiService {
    @GET("makanan")
    suspend fun getMakanan(): List<Makanan>

    @POST("makanan")
    suspend fun addMakanan(@Body makanan: Makanan)

    @DELETE("makanan/{id}")
    suspend fun deleteMakanan(@Path("id") id: String)
}

object MakananApi {
    val service: MakananApiService by lazy {
        retrofit.create(MakananApiService::class.java)
    }

    fun getMakananUrl(gambar: String): String {
        return "$gambar"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }