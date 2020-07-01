package com.mvvmarvel.data.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceCreator {

    companion object {

        fun <T> createService(serviceClass: Class<T>) : T {
            val retrofit = Retrofit.Builder()
                .baseUrl(NetworkUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(serviceClass)
        }
    }
}