package com.project.starwarsquiz

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

// Define a Retrofit interface
interface StarWarsApiService {

    @GET("{resource}?format=json")
    fun fetchData(@Path("resource") resource: String): Call<Any>

    @GET
    fun planet(@Url url: String): Call<Planet>

    @GET("planets")
    fun planets(): Call<ApiResponse<Planet>>

    @GET("people")
    fun characters(): Call<ApiResponse<Character>>

    @GET("starships")
    fun ships(): Call<ApiResponse<Ship>>

    @GET("species")
    fun species(): Call<ApiResponse<Species>>
}

//    @GET("people")
//    fun characters(): ApiResponse<Character>

internal val moshi by lazy {
    Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

// Create the Service that communicates with the Star Wars API
val service: StarWarsApiService = Retrofit.Builder()
    .client(OkHttpClient().newBuilder().build())
    .baseUrl("https://swapi.dev/api/")
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()
    .create(StarWarsApiService::class.java)

//@JsonClass(generateAdapter = true)
data class ApiResponse<T>(
    val count: Int,
    val results: List<T>
)