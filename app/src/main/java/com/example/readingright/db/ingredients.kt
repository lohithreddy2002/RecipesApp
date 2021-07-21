package com.example.readingright.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ingredients(
    val ingredients: List<String?>,
    val measures: List<String?>
) : Parcelable