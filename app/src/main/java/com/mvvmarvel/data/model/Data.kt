package com.mvvmarvel.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    @SerializedName("results")
    val marvelCharacters: List<MarvelCharacter>,
    val total: Int
) : Parcelable