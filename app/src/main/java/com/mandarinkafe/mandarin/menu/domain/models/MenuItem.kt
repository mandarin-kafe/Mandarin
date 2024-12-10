package com.mandarinkafe.mandarin.menu.domain.models

sealed class MenuItem {
    data class Header(val categoryName: String) : MenuItem()
    data class MealItem(val meal: Meal) : MenuItem()
}