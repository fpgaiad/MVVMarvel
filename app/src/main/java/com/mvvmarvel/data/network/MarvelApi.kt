package com.mvvmarvel.data.network

import com.mvvmarvel.data.model.MarvelResponse
import io.reactivex.Single

class MarvelApi {

    companion object {

        fun getCharacters(
            offset: Int,
            orderBy: String,
            timeStamp: String,
            publicKey: String,
            hash: String
        ): Single<MarvelResponse> {
            val apiService =
                ServiceCreator.createService(MarvelApiService::class.java)

            return apiService.getCharacters(
                offset,
                orderBy,
                timeStamp,
                publicKey,
                hash
            )
        }

        fun getCharactersByQuery(
            query: String,
            offset: Int,
            orderBy: String,
            timeStamp: String,
            publicKey: String,
            hash: String
        ): Single<MarvelResponse> {
            val apiService =
                ServiceCreator.createService(MarvelApiService::class.java)

            return apiService.getCharactersByQuery(
                query,
                offset,
                orderBy,
                timeStamp,
                publicKey,
                hash
            )
        }

    }
}