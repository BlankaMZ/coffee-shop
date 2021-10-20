package com.example.coffeeshop.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.coffeeshop.R
import com.example.coffeeshop.data.model.Manufacturer
import com.example.coffeeshop.data.model.Product
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

@BindingAdapter("manufacturer", "manufacturerHolder", requireAll = true)
fun TextView.showManufacturerName(manufacturer: Manufacturer, manufacturerHolder: String) {
    text = "${manufacturerHolder}: ${manufacturer.name}"
}

@BindingAdapter("price", "currencyHolder", requireAll = true)
fun TextView.showProductPriceWithCurrency(price: String, currencyHolder: String) {
    text = "$price $currencyHolder"
}

@BindingAdapter("imageUrl", "error", requireAll = true)
fun showProductImage(view: ImageView, imageUrl: String, error: Drawable) {
    Picasso.get().load(imageUrl).error(error).into(view)
}