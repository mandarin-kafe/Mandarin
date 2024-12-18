package com.mandarinkafe.mandarin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mandarinkafe.mandarin.menu.domain.api.FavoritesInteractor
import com.mandarinkafe.mandarin.menu.domain.api.MenuInteractor
import com.mandarinkafe.mandarin.menu.domain.models.Item
import com.mandarinkafe.mandarin.menu.domain.models.ItemCategory
import com.mandarinkafe.mandarin.menu.domain.models.MenuItem
import com.mandarinkafe.mandarin.menu.domain.models.mockMenuData

class MockMenuViewModel(
    private val menuInteractor: MenuInteractor,
    private val favoritesInteractor: FavoritesInteractor
) : ViewModel() {
    private var menuCategories = mutableListOf<ItemCategory>()
    private var menuItems = mutableListOf<MenuItem>()

    init {
        menuCategories = mockMenuData
        menuCategories.forEach { category ->
            menuItems.add(MenuItem.Category(category.name))
            menuItems.addAll(category.items.map { MenuItem.MealItem(it) })
        }
    }

    fun prepareMenuItems() {
        screenState.value =
            ScreenState.Content(menu = mockMenuData, menuItems = menuItems)
    }

    private var screenState = MutableLiveData<ScreenState>(ScreenState.Loading)
    fun getScreenState(): LiveData<ScreenState> = screenState

    fun getMenu() {
    }

    fun toggleFavorite(item: Item) {
        if (item.isFavorite) {
            favoritesInteractor.removeFromFavorites(item)
        } else {
            favoritesInteractor.addToFavorites(item)
        }
//        updateMealContent(item, item.copy(isFavorite = !item.isFavorite))

    }

    private fun updateMealContent(oldItem: Item, newItem: Item) {
        val currentState = screenState.value
        if (currentState is ScreenState.Content) {
            val itemIndex = currentState.menuItems.indexOfFirst { item ->
                item is MenuItem.MealItem && item.meal == oldItem
            }
            if (itemIndex != -1) {
                screenState.value = ScreenState.Content(currentState.menu,
                    currentState.menuItems.also {
                        it[itemIndex] = MenuItem.MealItem(newItem)
                    }
                )
            }
        }

    }
}



