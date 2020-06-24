package com.mvvmarvel.repository

import androidx.lifecycle.MutableLiveData
import com.mvvmarvel.model.Result
import com.mvvmarvel.model.Thumbnail

object MarvelFavoriteRepository : MarvelRepository {

    private var mResult: MutableList<Result> = mutableListOf()
    private var data: MutableLiveData<List<Result>> = MutableLiveData(emptyList())

    override fun getCharacters(): MutableLiveData<List<Result>> {
        if (data.value?.isEmpty()!!) setResults()
        data.value = mResult
        return data
    }

    private fun setResults() {
        mResult.run {
            add(
                Result(
                    "This is the FIRST short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "1 FAVORITE Marvel 1",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
            add(
                Result(
                    "This is the SECOND short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "2 FAVORITE Marvel 2",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
            add(
                Result(
                    "This is the THIRD short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "3 FAVORITE Marvel 3",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
            add(
                Result(
                    "This is the FORTH short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "4 FAVORITE Marvel 4",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
            add(
                Result(
                    "This is the FIFTH short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "5 FAVORITE Marvel 5",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
            add(
                Result(
                    "This is the SIXTH short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "6 FAVORITE Marvel 6",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
            add(
                Result(
                    "This is the SEVENTH short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "7 FAVORITE Marvel 7",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
            add(
                Result(
                    "This is the EIGHT short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "8 FAVORITE Marvel 8",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
            add(
                Result(
                    "This is the NINE short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "9 FAVORITE Marvel 9",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
            add(
                Result(
                    "This is the TENTH short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "10 FAVORITE Marvel 10",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
            add(
                Result(
                    "This is the ELEVENTH short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "11 FAVORITE Marvel 11",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
            add(
                Result(
                    "This is the TWELVE short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "12 FAVORITE Marvel 12",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
        }
    }
}