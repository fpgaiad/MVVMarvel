package com.mvvmarvel.data.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceCreator {

    companion object {

        fun <T> createService(serviceClass: Class<T>) : T {
            val retrofit = Retrofit.Builder()
                .baseUrl(NetworkUrl.BASE_URL)
//                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(serviceClass)
        }

//        private fun provideOkHttpClient(): OkHttpClient {
//            val httpClient = OkHttpClient.Builder()
//            httpClient.connectTimeout(10, TimeUnit.SECONDS)
//            httpClient.readTimeout(10, TimeUnit.SECONDS)
//            return httpClient.build()
//        }
    }
}