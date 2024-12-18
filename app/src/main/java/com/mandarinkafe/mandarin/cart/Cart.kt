package com.mandarinkafe.mandarin.cart

import com.mandarinkafe.mandarin.menu.domain.models.Item

object Cart {
    val items: MutableList<Item> = mutableListOf()

    fun addItem(item: Item) {
        items.add(item)
    }

    fun clear() {
        items.clear()
    }
}