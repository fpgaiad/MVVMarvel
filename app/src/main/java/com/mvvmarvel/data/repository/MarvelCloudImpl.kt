package com.mvvmarvel.data.repository

import com.mvvmarvel.BuildConfig.*
import com.mvvmarvel.data.model.MarvelResponse
import com.mvvmarvel.data.network.MarvelApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MarvelCloudImpl : MarvelCloud {

    override fun getCharacters(): Single<MarvelResponse> {
        return MarvelApi.getCharacters(ORDER_BY, TIME_STAMP, PUBLIC_KEY, HASH)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}