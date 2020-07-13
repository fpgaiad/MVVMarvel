package com.mvvmarvel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvmarvel.data.model.MarvelCharacter
import com.mvvmarvel.data.model.MarvelResponse
import com.mvvmarvel.data.repository.MarvelRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersViewModel(
    private val repository: MarvelRepository,
    val isFavoriteScreen: Boolean = false
) : ViewModel() {

    private var mQuery = ""
    private var totalListSize = 0
    private var disposables = CompositeDisposable()
    var error = MutableLiveData<Throwable>()
    var offset = 0
    var characters = MutableLiveData<List<MarvelCharacter>>()
    var isFromLoadMore: Boolean = false
    var isLastPage: Boolean = false

    companion object {
        private const val PAGE_SIZE = 20
    }

    fun getCharacters(): LiveData<List<MarvelCharacter>> {
        if (!isFromLoadMore) resetOffset()
        when {
            isFavoriteScreen -> {
                viewModelScope.launch {
                    characters.value = withContext(Dispatchers.IO) {
                        getFavorites()
                    }
                }
            }
            else -> {
                disposables.add(
                    repository.getCharacters(offset)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            { setResponse(it) },
                            { error.value = it }
                        )
                )
            }
        }
        return characters
    }

    suspend fun getFavorites(): List<MarvelCharacter> {
        return repository.getFavorites().toMutableList()
    }

    fun getCharactersByQuery(query: String) {
        if (!isFromLoadMore) resetOffset()
        setQuery(query)
        disposables.add(
            repository.getCharactersByQuery(query, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { setResponse(it) },
                    { error.value = it }
                ))
    }

    fun resetOffset() {
        offset = 0
    }

    fun saveCharacter(marvelCharacter: MarvelCharacter) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.saveFavorite(marvelCharacter)
            }
        }
    }

    fun deleteCharacter(marvelCharacter: MarvelCharacter) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.deleteFavorite(marvelCharacter)
            }
        }
    }

    fun loadMore() {
        setPage()
        callDispatcher(true)
    }

    private fun callDispatcher(isLoadMore: Boolean) {
        isFromLoadMore = isLoadMore
        when {
            mQuery.isBlank() -> {
                getCharacters()
            }
            else -> {
                getCharactersByQuery(mQuery)
            }
        }
        isFromLoadMore = false
    }

    private fun retry(isLoadMore: Boolean) {
        callDispatcher(isLoadMore)
    }

    private fun setResponse(it: MarvelResponse) {
        characters.value = it.data.marvelCharacters
        totalListSize = it.data.total
    }

    private fun setPage() {
        if ((offset + PAGE_SIZE) < totalListSize) {
            isLastPage = false
            offset += PAGE_SIZE
        } else {
            isLastPage = true
        }
    }

    fun setQuery(query: String) {
        mQuery = query
    }

    override fun onCleared() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }
}
