package com.mandarinkafe.mandarin.menu.domain.api

import com.mandarinkafe.mandarin.menu.domain.models.Item

interface FavoritesInteractor {
    fun addToFavorites(item: Item)
    fun removeFromFavorites(item: Item)
}