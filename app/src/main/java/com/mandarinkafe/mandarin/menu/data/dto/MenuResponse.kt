package com.mandarinkafe.mandarin.menu.data.dto

data class MenuResponse(
    val itemCategories: List<ItemCategory>,
) : Response()

data class ItemCategory(
    val id: String,
    val name: String,
    val items: List<Item>,
    )

data class Item(
    val itemId: String,
    val sku: String,
    val name: String,
    val description: String,
    val tags: List<Tag>,
    val itemSizes: List<ItemSize>,

)

data class Tag(
    val id: String,
    val name: String
)

data class ItemSize(
    val portionWeightGrams: Int,

)
