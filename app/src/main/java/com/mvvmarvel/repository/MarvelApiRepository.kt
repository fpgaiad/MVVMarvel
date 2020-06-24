package com.mvvmarvel.repository

import androidx.lifecycle.MutableLiveData
import com.mvvmarvel.model.Result
import com.mvvmarvel.model.Thumbnail

object MarvelApiRepository : MarvelRepository {

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
                    "1 Marvel Character 1",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
            add(
                Result(
                    "This is the SECOND short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "2 Marvel Character 2",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
            add(
                Result(
                    "This is the THIRD short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "3 Marvel Character 3",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
            add(
                Result(
                    "This is the FORTH short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "4 Marvel Character 4",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
            add(
                Result(
                    "This is the FIFTH short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "5 Marvel Character 5",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
            add(
                Result(
                    "This is the SIXTH short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "6 Marvel Character 6",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
            add(
                Result(
                    "This is the SEVENTH short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "7 Marvel Character 7",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
            add(
                Result(
                    "This is the EIGHT short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "8 Marvel Character 8",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
            add(
                Result(
                    "This is the NINE short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "9 Marvel Character 9",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
            add(
                Result(
                    "This is the TENTH short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "10 Marvel Character 10",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
            add(
                Result(
                    "This is the ELEVENTH short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "11 Marvel Character 11",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
            add(
                Result(
                    "This is the TWELVE short description about this character, but this description could be a little longer",
                    2345,
                    "modified String date",
                    "12 Marvel Character 12",
                    "https://alskdjflaskdjf;laksjfljasdlkfjas;lfjals;f",
                    Thumbnail("jpg", "https://laskdjf;alskjf")
                )
            )
        }
    }
}