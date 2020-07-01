package com.mvvmarvel.data.network

import com.mvvmarvel.data.model.MarvelResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {
    @GET(NetworkUrl.URL_ACTION_GET_CHARACTERS)
    fun getCharacters(
        @Query("orderBy") orderBy: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Single<MarvelResponse>
}