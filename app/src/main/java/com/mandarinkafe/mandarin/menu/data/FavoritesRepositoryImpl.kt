package com.mandarinkafe.mandarin.menu.data

import com.mandarinkafe.mandarin.menu.domain.api.FavoritesRepository
import com.mandarinkafe.mandarin.menu.domain.models.Item

class FavoritesRepositoryImpl(private val localStorage: LocalStorage) : FavoritesRepository {
    override fun addToFavorites(item: Item) {
        localStorage.addToFavorites(item.id)
    }

    override fun removeFromFavorites(item: Item) {
        localStorage.removeFromFavorites(item.id)
    }
    override fun getFavoriteIds() : List<String> {
        return localStorage.getSavedFavorites().toList()
    }
}
