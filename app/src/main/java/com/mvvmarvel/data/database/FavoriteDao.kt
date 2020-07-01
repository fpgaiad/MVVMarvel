package com.mvvmarvel.data.database

import androidx.room.*
import com.mvvmarvel.data.model.MarvelCharacter

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites")
    suspend fun getFavorites(): List<MarvelCharacter>

    @Query("SELECT * FROM favorites WHERE name = :name")
    suspend fun getFavoriteByName(name: String): MarvelCharacter

    @Delete
    suspend fun deleteFavorite(favorite: MarvelCharacter)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateFavorite(favorite: MarvelCharacter)
}