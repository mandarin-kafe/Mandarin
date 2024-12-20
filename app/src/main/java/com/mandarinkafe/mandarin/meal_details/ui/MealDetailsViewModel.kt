package com.mandarinkafe.mandarin.meal_details.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mandarinkafe.mandarin.menu.domain.api.FavoritesInteractor
import com.mandarinkafe.mandarin.menu.domain.models.Meal
import kotlinx.coroutines.launch

class MealDetailsViewModel(
    private var meal: Meal,
    private val favoritesInteractor: FavoritesInteractor
) : ViewModel() {

    private var isFavoriteLiveData = MutableLiveData<Boolean>()
    fun getIsFavoriteLiveData(): LiveData<Boolean> {
        return isFavoriteLiveData
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            if (meal.isFavorite) {
                favoritesInteractor.removeFromFavorites(meal)
            } else {
                favoritesInteractor.addToFavorites(meal)
            }
        }
        isFavoriteLiveData.value = !meal.isFavorite
        val newMeal = meal.copy(isFavorite = !meal.isFavorite)
        this.meal = newMeal
    }
}