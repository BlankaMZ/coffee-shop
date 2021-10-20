package com.example.coffeeshop.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ProductImage(
    val image: String,
    val order: Int
) : Parcelable