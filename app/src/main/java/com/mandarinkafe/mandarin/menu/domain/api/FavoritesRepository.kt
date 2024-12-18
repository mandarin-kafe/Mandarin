package com.mandarinkafe.mandarin.menu.domain.api

import com.mandarinkafe.mandarin.menu.domain.models.Item

interface FavoritesRepository {
    fun addToFavorites(item: Item)
    fun removeFromFavorites(item: Item)
    fun getFavoriteIds() : List<String>
}


