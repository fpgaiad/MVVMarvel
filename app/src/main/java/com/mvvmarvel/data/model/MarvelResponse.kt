package com.mvvmarvel.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarvelResponse(
    val code: Int,
    val status: String,
    val `data`: Data
) : Parcelable