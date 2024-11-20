package com.mandarinkafe.mandarin.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mandarinkafe.mandarin.domain.models.Meal
import com.mandarinkafe.mandarin.domain.models.MenuCategory
import com.mandarinkafe.mandarin.domain.models.mockMenuData

class MenuViewModel(

) : ViewModel() {
    private var menuState = MutableLiveData<MenuScreenState>(MenuScreenState.Loading)
    fun getMenuState(): LiveData<MenuScreenState> = menuState


    fun prepareMenuItems() {
//        //TODO вместо mockMenuData добавить сетевой запрос на получение актуального меню.
//        // Конвертация в List<MenuItem> должна происходить в дата слое!!!
//
//        val menuItems = mutableListOf<MenuItem>()
//        mockMenuData.forEach { category ->
//            menuItems.add(MenuItem.Header(category.name))
//            menuItems.addAll(category.items.map { MenuItem.MealItem(it) })
//        }
        menuState.value = MenuScreenState.Content(mockMenuData)
    }


    fun toggleFavorite(meal: Meal) {
//TODO пример можно взять из MovieSearcher
    }


    sealed interface MenuScreenState {
        data object Loading : MenuScreenState
        data object NetworkError : MenuScreenState
        data class Content(var menuCategories: ArrayList<MenuCategory>) :
            MenuScreenState

//    data class Content(var menuItems: List<MenuItem>, var menuCategories: ArrayList<MenuCategory>) :
//        MenuScreenState

    }
}
