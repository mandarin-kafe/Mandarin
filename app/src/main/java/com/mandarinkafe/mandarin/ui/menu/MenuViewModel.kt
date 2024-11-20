package com.mandarinkafe.mandarin.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mandarinkafe.mandarin.domain.Meal
import com.mandarinkafe.mandarin.domain.mockMealkList

class MenuViewModel(

) : ViewModel() {

    private val showMealDetailsTrigger = MutableLiveData<Meal>()
    fun getPlayerTrigger(): LiveData<Meal> = showMealDetailsTrigger

    private var menuState = MutableLiveData<MenuScreenState>(MenuScreenState.Loading)
    fun getMenuState(): LiveData<MenuScreenState> = menuState


    fun getMenu() {
        //TODO вместо этого добавить сетевой запрос на получение актуального меню
        menuState.value = MenuScreenState.Content(mockMealkList)
    }

    fun onMealClick(meal: Meal) {
        showMealDetailsTrigger.value = meal
    }
}


sealed interface MenuScreenState {
    data object Loading : MenuScreenState
    data object NetworkError : MenuScreenState
    data class Content(var mealList: ArrayList<Meal>) : MenuScreenState

}