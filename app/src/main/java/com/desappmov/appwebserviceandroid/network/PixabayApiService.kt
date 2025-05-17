package com.desappmov.appwebserviceandroid.network

import com.desappmov.appwebserviceandroid.model.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApiService {
    @GET("api/")
    suspend fun searchPhotos(
        @Query("key") apiKey: String,
        @Query("q") query: String,
        @Query("image_type") imageType: String = "photo",
        @Query("orientation") orientation: String = "all",
        @Query("lang") lang: String = "es"
    ): PhotoResponse


    @GET("api/")
    suspend fun searchPhotoById(
        @Query("key") apiKey: String,
        @Query("id") query: String
    ): PhotoResponse

}