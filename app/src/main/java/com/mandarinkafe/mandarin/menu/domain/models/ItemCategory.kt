package com.mandarinkafe.mandarin.menu.domain.models

data class ItemCategory(
    val id: String,
    val name: String,
    val items: List<Item>
)

val mockMenuData = arrayListOf<ItemCategory>(
    ItemCategory("d3872541-5a16-4c21-b9e7-c8ab8912fd36","ПИЦЦА", mockPizzaList),
    ItemCategory("903872541-5a16-3533-b9e7-c8ab8912fd36","СУШИ И РОЛЛЫ", mockSushiList),
    ItemCategory("c21-b9e7-c8ab8912fd44444909090544436","БУРГЕРЫ", mockBurgerList),
    ItemCategory("d32222222222222b891246465554fd36","ХОТ-ДОГИ И ДОНЕР", mockHotDogList),
    ItemCategory("d3872541-5a16-444448912555f9090d36","WOK", mockWokList)


)


