package com.mvvmarvel.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mvvmarvel.data.model.MarvelCharacter

private const val DATABASE = "favorites"

@Database(
    entities = [MarvelCharacter::class],
    version = 4,
    exportSchema = false
)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {

        @Volatile
        private var instance: FavoriteDatabase? = null

        fun getInstance(context: Context): FavoriteDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): FavoriteDatabase {
            return Room.databaseBuilder(context, FavoriteDatabase::class.java, DATABASE)
                .build()
        }
    }
}