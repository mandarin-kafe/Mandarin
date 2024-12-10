package com.mandarinkafe.mandarin.menu.data

import com.mandarinkafe.mandarin.menu.domain.api.FavoritesRepository
import com.mandarinkafe.mandarin.menu.domain.models.Meal

class FavoritesRepositoryImpl(private val localStorage: LocalStorage) : FavoritesRepository {
    override fun addToFavorites(meal: Meal) {
        localStorage.addToFavorites(meal.id)
    }

    override fun removeFromFavorites(meal: Meal) {
        localStorage.removeFromFavorites(meal.id)
    }

}