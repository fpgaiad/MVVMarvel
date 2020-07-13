package com.mvvmarvel.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mvvmarvel.data.model.Data
import com.mvvmarvel.data.model.MarvelCharacter
import com.mvvmarvel.data.model.MarvelResponse
import com.mvvmarvel.data.model.Thumbnail
import com.mvvmarvel.data.network.MarvelApiService
import com.mvvmarvel.data.repository.MarvelRepository
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharactersViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MarvelRepository

    @Mock
    private lateinit var marvelCharacter: MarvelCharacter

    @Mock
    private lateinit var charactersLiveDataObserver: Observer<List<MarvelCharacter>>

    private var isFavoriteScreen = false

    private lateinit var viewModel: CharactersViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when getCharacters and is FavoriteScreen then call repository getFavorites`() {
        isFavoriteScreen = true
        viewModel = CharactersViewModel(repository, isFavoriteScreen)

        viewModel.getCharacters()

        runBlocking {
            verify(repository, times(1)).getFavorites()
        }
    }


    @Test
    fun `when saveCharacter then call repository saveFavorite`() {
        viewModel = CharactersViewModel(repository, isFavoriteScreen)

        viewModel.saveCharacter(marvelCharacter)

        runBlocking {
            verify(repository, times(1)).saveFavorite(marvelCharacter)
        }
    }

    @Test
    fun `when deleteCharacter then call repository deleteFavorite`() {
        viewModel = CharactersViewModel(repository, isFavoriteScreen)

        viewModel.deleteCharacter(marvelCharacter)

        runBlocking {
            verify(repository, times(1)).deleteFavorite(marvelCharacter)
        }
    }
    

    private fun getMockMarvelResponse(): MarvelResponse {
        return MarvelResponse(
            code = anyInt(),
            status = anyString(),
            data = Data(
                count = anyInt(),
                limit = anyInt(),
                offset = anyInt(),
                marvelCharacters = getMockMarvelCharacters(),
                total = anyInt()
            )
        )
    }

    private fun getMockMarvelCharacters(): List<MarvelCharacter> {
        return listOf(
            MarvelCharacter(
                description = anyString(),
                id = anyInt(),
                modified = anyString(),
                name = anyString(),
                resourceURI = anyString(),
                thumbnail = Thumbnail(
                    extension = anyString(),
                    path = anyString()
                )
            )
        )
    }

}

class MockRepository(
    private val marvelCharacters: List<MarvelCharacter>? = null,
    private val marvelCharacter: MarvelCharacter? = null,
    private val marvelResponse: MarvelResponse? = null
) : MarvelRepository {
    override fun getCharacters(offset: Int): Single<MarvelResponse> {
        return Single.create { marvelResponse!! }
    }

    override fun getCharactersByQuery(query: String, offset: Int): Single<MarvelResponse> {
        return Single.create { marvelResponse!! }
    }

    override suspend fun getFavorite(id: Int): MarvelCharacter {
        return marvelCharacter!!
    }

    override suspend fun getFavorites(): List<MarvelCharacter> {
        return marvelCharacters!!
    }

    override suspend fun saveFavorite(character: MarvelCharacter): Long {
        return 0
    }

    override suspend fun deleteFavorite(character: MarvelCharacter): Int {
        return 0
    }

}