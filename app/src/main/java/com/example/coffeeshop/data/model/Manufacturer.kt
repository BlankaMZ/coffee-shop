package com.example.coffeeshop.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Manufacturer(
    var slug: String,
    var name: String
) : Parcelable