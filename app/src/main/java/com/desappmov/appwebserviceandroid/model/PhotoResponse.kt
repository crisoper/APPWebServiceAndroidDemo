package com.desappmov.appwebserviceandroid.model

data class PhotoResponse (
    val total: Int,
    val totalHits: Int,
    val hits: List<Photo>
)