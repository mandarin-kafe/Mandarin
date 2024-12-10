package com.mandarinkafe.mandarin.cart

import com.mandarinkafe.mandarin.menu.domain.models.Meal

object Cart {
    val items: MutableList<Meal> = mutableListOf()

    fun addItem(meal: Meal) {
        items.add(meal)
    }

    fun clear() {
        items.clear()
    }
}