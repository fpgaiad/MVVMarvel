package com.mvvmarvel.model

data class MarvelResponse(
    val code: Int,
    val status: String,
    val `data`: Data
)