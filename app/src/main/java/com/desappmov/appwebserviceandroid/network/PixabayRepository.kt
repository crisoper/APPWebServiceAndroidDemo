package com.desappmov.appwebserviceandroid.network

import com.desappmov.appwebserviceandroid.model.PhotoResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PixabayRepository  {

    private val api: PixabayApiService

    companion object {
        private const val BASE_URL = "https://pixabay.com/"
        // Cambiar por tu propia API KEY
        private const val API_KEY = ""
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(PixabayApiService::class.java)
    }

    suspend fun getPhotos(query: String, orientation: String): PhotoResponse {
        return api.searchPhotos(
            apiKey = API_KEY,
            query = query,
            orientation = orientation
        )
    }

}
