package com.project.starwarsquiz

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

// Define a Retrofit interface
interface StarWarsApiService {
    // For fetching from the api for a singular planet from the URL
    @GET
    fun planet(@Url url: String): Call<Planet>

    // Get all Planets from the API
    @GET("planets")
    fun planets(): Call<ApiResponse<Planet>>

    // Get all people/characters from the API
    @GET("people")
    fun characters(): Call<ApiResponse<Character>>

    // Get all Ships from the API
    @GET("starships")
    fun ships(): Call<ApiResponse<Ship>>

    // Get all Species from the API
    @GET("species")
    fun species(): Call<ApiResponse<Species>>
}

// For convertingg into specific classes (e.g. Ship, Planet, Character)
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
//^ make it so that the service can use the routes outlined in the interface

// Allows us to see the .results autocomplete when you are reading from the API and define
// results as a List of T where T could be Planet, Ship, Character, etc (any of the defined
// classes in
// Models.kt)
data class ApiResponse<T>(
    val results: List<T>
)