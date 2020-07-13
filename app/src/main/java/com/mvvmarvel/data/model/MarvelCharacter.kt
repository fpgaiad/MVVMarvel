package com.mvvmarvel.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "favorites")
@Parcelize
data class MarvelCharacter(

    val description: String,
    @PrimaryKey
    val id: Int,
    val modified: String,
    val name: String,
    @ColumnInfo(name = "resource_uri")
    val resourceURI: String,
    @Embedded(prefix = "thumbnail_")
    val thumbnail: Thumbnail
) : Parcelable