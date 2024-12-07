package com.mandarinkafe.mandarin.ui.cart

import android.util.Log
import com.mandarinkafe.mandarin.domain.models.Meal

object Cart {
    val items: MutableList<Meal> = mutableListOf()

    fun addItem(meal: Meal) {
        items.add(meal)
    }

    fun clear() {
        items.clear()
    }
}