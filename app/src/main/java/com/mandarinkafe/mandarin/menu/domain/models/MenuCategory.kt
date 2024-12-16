package com.mandarinkafe.mandarin.menu.domain.models

data class MenuCategory(
    val name: String,
    val items: ArrayList<Meal>
)

val mockMenuData = arrayListOf<MenuCategory>(
    MenuCategory("ПИЦЦА", mockPizzaList),
    MenuCategory("СУШИ И РОЛЛЫ", mockSushiList),
    MenuCategory("БУРГЕРЫ", mockBurgerList),
    MenuCategory("ХОТ-ДОГИ И ДОНЕР", mockHotDogList),
    MenuCategory("WOK", mockWokList)


)


