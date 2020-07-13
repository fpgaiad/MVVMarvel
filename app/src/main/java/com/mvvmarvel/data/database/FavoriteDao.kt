package com.mvvmarvel.data.database

import androidx.room.*
import com.mvvmarvel.data.model.MarvelCharacter

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites")
    suspend fun getFavorites(): List<MarvelCharacter>

    @Query("SELECT * FROM favorites WHERE id = :id")
    suspend fun getFavoriteById(id: Int): MarvelCharacter

    @Delete
    suspend fun deleteFavorite(favorite: MarvelCharacter): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateFavorite(favorite: MarvelCharacter): Long
}