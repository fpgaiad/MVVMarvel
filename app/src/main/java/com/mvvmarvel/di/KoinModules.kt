package com.mvvmarvel.di

import com.mvvmarvel.data.database.FavoriteDao
import com.mvvmarvel.data.database.FavoriteDatabase
import com.mvvmarvel.data.repository.MarvelCloud
import com.mvvmarvel.data.repository.MarvelCloudImpl
import com.mvvmarvel.data.repository.MarvelRepository
import com.mvvmarvel.data.repository.MarvelRepositoryImpl
import com.mvvmarvel.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    single<MarvelCloud> { MarvelCloudImpl() }
    single<FavoriteDao> { FavoriteDatabase.getInstance(context = get()).favoriteDao() }
    single<MarvelRepository> { MarvelRepositoryImpl(cloud = get(), store = get()) }
    viewModel { (isFavorite: Boolean) ->
        CharactersViewModel(
            repository = get(),
            isFavorite = isFavorite
        )
    }
}