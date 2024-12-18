package com.mandarinkafe.mandarin

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

//   для демонстрации меню с мок-списком включить
//    init {
//        menuCategories = mockMenuData
//        menuCategories.forEach { category ->
//            menuItems.add(MenuItem.Header(category.name))
//            menuItems.addAll(category.items.map { MenuItem.MealItem(it) })
//        }
//    }
//      fun prepareMenuItems() {
//    screenState.value =
//        ScreenState.Content(menuCategories = mockMenuData, menuItems = menuItems)
//      }

    private var menuCategories = mutableListOf<ItemCategory>()
    private var menuItems = mutableListOf<MenuItem>()


    private var screenState = MutableLiveData<ScreenState>(ScreenState.Loading)
    fun getScreenState(): LiveData<ScreenState> = screenState

    fun getMenu() {
        screenState.postValue(ScreenState.Loading)
        viewModelScope.launch {
            menuInteractor.getMenu().collect { pair -> //   для демонстрации меню с мок-списком ВЫКЛЮЧИТЬ
                processMenuResult(pair.first, pair.second) //   для демонстрации меню с мок-списком ВЫКЛЮЧИТЬ
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
            screenState.postValue(ScreenState.Error(errorMessage))
        }
    }

    fun toggleFavorite(item: Item) {
        if (item.isFavorite) {
            favoritesInteractor.removeFromFavorites(item)
        } else {
            favoritesInteractor.addToFavorites(item)
        }
        updateMealContent(item.id, item.copy(isFavorite = !item.isFavorite))
    }

    private fun updateMealContent(mealId: String, newItem: Item) {
        val currentState = screenState.value

//        if (currentState is MenuScreenState.Content) {
//            val movieIndex = currentState.menuItems.indexOfFirst { it.id == mealId }
//            if (movieIndex != -1) {
//                menuState.value = MenuScreenState.Content(currentState.menuCategories,
//                    currentState.menuItems.toMutableList().also {
//                        it[movieIndex] = newMeal
//                    }
//                )
//            }
//        }

    }
}

    sealed interface ScreenState {
        data object Loading : ScreenState
        data class Error(var errorMessage: String) : ScreenState
        data class Content(var menu: List<ItemCategory>, var menuItems: MutableList<MenuItem>) :
            ScreenState
    }

