package com.mandarinkafe.mandarin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mandarinkafe.mandarin.menu.domain.api.FavoritesInteractor
import com.mandarinkafe.mandarin.menu.domain.api.MenuInteractor
import com.mandarinkafe.mandarin.menu.domain.models.Item
import com.mandarinkafe.mandarin.menu.domain.models.ItemCategory
import com.mandarinkafe.mandarin.menu.domain.models.MenuItem
import kotlinx.coroutines.launch

class SharedViewModel(
    private val menuInteractor: MenuInteractor,
    private val favoritesInteractor: FavoritesInteractor
) : ViewModel() {

    private var menuItems = mutableListOf<MenuItem>()
    private var screenState = MutableLiveData<ScreenState>(ScreenState.Loading)
    fun getScreenState(): LiveData<ScreenState> = screenState

    fun getMenu() {
        screenState.postValue(ScreenState.Loading)
        viewModelScope.launch {
            menuInteractor.getMenu()
                .collect { pair ->
                    processMenuResult(
                        pair.first,
                        pair.second
                    )
                }
        }
    }

    private fun processMenuResult(menu: List<ItemCategory>?, errorMessage: String?) {
        if (!menu.isNullOrEmpty()) {
            menu.forEach { category ->
                menuItems.add(MenuItem.Category(category.name))
                menuItems.addAll(category.items.map { MenuItem.MealItem(it) })

            }
            screenState.postValue(ScreenState.Content(menu, menuItems))
        }
        if (errorMessage != null) {
            screenState.postValue(ScreenState.Error)
            Log.e("ERROR", "$errorMessage")
        }
    }

    fun toggleFavorite(item: Item) {
        if (item.isFavorite) {
            favoritesInteractor.removeFromFavorites(item)
        } else {
            favoritesInteractor.addToFavorites(item)
        }

    }

}

sealed interface ScreenState {
    data object Loading : ScreenState
    data object Error : ScreenState
    data class Content(var menu: List<ItemCategory>, var menuItems: MutableList<MenuItem>) :
        ScreenState
}

