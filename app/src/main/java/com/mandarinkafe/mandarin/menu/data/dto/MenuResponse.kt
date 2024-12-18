package com.mandarinkafe.mandarin.menu.data.dto

data class MenuResponse(
    val itemCategories: List<ItemCategoryDto>,
) : Response()

data class ItemCategoryDto(
    val id: String,
    val name: String,
    val items: List<ItemDto>,
)

data class ItemDto(
    val itemId: String,
    val sku: String,
    val name: String,
    val description: String,
    val tags: List<Tag>,
    val itemSizes: List<ItemSize>
    )

data class Tag(
    val id: String,
    val name: String
)

data class ItemSize(
    val portionWeightGrams: Int,
    val prices: List<Price>,
)

data class Price(
    val organizationId: String,
    val price: Double
)

