package com.mandarinkafe.mandarin.meal_details.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mandarinkafe.mandarin.menu.domain.api.FavoritesInteractor
import com.mandarinkafe.mandarin.menu.domain.models.Item
import kotlinx.coroutines.launch

class MealDetailsViewModel(
    private var item: Item,
    private val favoritesInteractor: FavoritesInteractor
) : ViewModel() {

    private var isFavoriteLiveData = MutableLiveData<Boolean>()
    fun getIsFavoriteLiveData(): LiveData<Boolean> {
        return isFavoriteLiveData
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            if (item.isFavorite) {
                favoritesInteractor.removeFromFavorites(item)
            } else {
                favoritesInteractor.addToFavorites(item)
            }
        }
        isFavoriteLiveData.value = !item.isFavorite
        val newMeal = item.copy(isFavorite = !item.isFavorite)
        this.item = newMeal
    }
}