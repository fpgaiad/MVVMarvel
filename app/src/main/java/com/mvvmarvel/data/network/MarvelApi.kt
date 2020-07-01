package com.mvvmarvel.data.network

import com.mvvmarvel.data.model.MarvelResponse
import io.reactivex.Single

class MarvelApi {

    companion object {

        fun getCharacters(
            orderBy: String,
            ts: String,
            apiKey: String,
            hash: String
        ): Single<MarvelResponse> {

            val apiService =
                ServiceCreator.createService(MarvelApiService::class.java)
            return apiService.getCharacters(orderBy, ts, apiKey, hash)
        }

    }
}