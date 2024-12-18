package com.mandarinkafe.mandarin.menu.domain.models

sealed class MenuItem {
    open class Category(val categoryName: String) : MenuItem()
    class SubCategory(categoryName: String, val parentCategoryName: String) : Category(categoryName)
    class MealItem(val meal: Item) : MenuItem()

}