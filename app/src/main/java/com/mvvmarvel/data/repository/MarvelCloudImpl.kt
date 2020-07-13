package com.mvvmarvel.data.repository

import com.mvvmarvel.BuildConfig.*
import com.mvvmarvel.data.model.MarvelResponse
import com.mvvmarvel.data.network.MarvelApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MarvelCloudImpl : MarvelCloud {

    override fun getCharacters(offset: Int): Single<MarvelResponse> {
        return MarvelApi.getCharacters(offset, ORDER_BY, TIME_STAMP, PUBLIC_KEY, HASH)
    }

    override fun getCharactersByQuery(query: String, offset: Int): Single<MarvelResponse> {
        return  MarvelApi.getCharactersByQuery(query, offset, ORDER_BY, TIME_STAMP, PUBLIC_KEY, HASH)
    }
}