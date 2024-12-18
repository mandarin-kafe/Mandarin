package com.mandarinkafe.mandarin.menu.domain.impl

import com.mandarinkafe.mandarin.menu.domain.api.FavoritesInteractor
import com.mandarinkafe.mandarin.menu.domain.api.FavoritesRepository
import com.mandarinkafe.mandarin.menu.domain.models.Item

class FavoritesInteractorImpl(private val repository: FavoritesRepository): FavoritesInteractor {
    override fun addToFavorites(item: Item) {
        repository.addToFavorites(item) }

    override fun removeFromFavorites(item: Item) {
        repository.removeFromFavorites(item)
    }
}