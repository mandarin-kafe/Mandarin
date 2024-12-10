package com.mandarinkafe.mandarin.menu.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mandarinkafe.mandarin.menu.domain.models.Meal
import com.mandarinkafe.mandarin.menu.domain.models.MenuCategory
import com.mandarinkafe.mandarin.menu.domain.models.MenuItem
import com.mandarinkafe.mandarin.menu.domain.models.mockMenuData

class MenuViewModel(

) : ViewModel() {
    private var menuState = MutableLiveData<MenuScreenState>(MenuScreenState.Loading)
    private var menuItems = mutableListOf<MenuItem>()
    private var menuCategories = arrayListOf<MenuCategory>()

    init {
        menuCategories = mockMenuData
        menuCategories.forEach { category ->
            menuItems.add(MenuItem.Header(category.name))
            menuItems.addAll(category.items.map { MenuItem.MealItem(it) })
            // TODO убрать это отсюда! Обработка данных должна быть в дата слое.
        }
    }

    fun getMenuState(): LiveData<MenuScreenState> = menuState


    fun prepareMenuItems() {
        //TODO добавить сетевой запрос на получение актуального меню.
        menuState.value =
            MenuScreenState.Content(menuCategories = mockMenuData, menuItems = menuItems)
    }


    fun toggleFavorite(meal: Meal) {
//TODO пример можно взять из MovieSearcher
    }


    sealed interface MenuScreenState {
        data object Loading : MenuScreenState
        data object NetworkError : MenuScreenState
        data class Content(
            var menuCategories: ArrayList<MenuCategory>,
            var menuItems: MutableList<MenuItem>
        ) :
            MenuScreenState


    }
}
