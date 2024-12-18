package com.mandarinkafe.mandarin.menu.data

import com.mandarinkafe.mandarin.menu.data.dto.ItemCategoryDto
import com.mandarinkafe.mandarin.menu.data.dto.ItemDto
import com.mandarinkafe.mandarin.menu.domain.models.Item
import com.mandarinkafe.mandarin.menu.domain.models.ItemCategory

fun ItemCategoryDto.toDomain(storedFavorites: List<String>) = ItemCategory(
    id = id,
    name = name,
    items = items.map { it.toDomain(storedFavorites, id) }
)

fun ItemDto.toDomain(storedFavorites: List<String>, categoryId: String) = Item(
    id = itemId,
    sku = sku,
    name = name,
    description = description,
    weight = itemSizes.firstOrNull()?.portionWeightGrams ?: 0,
    price = itemSizes.firstOrNull()?.prices?.firstOrNull()?.price?.toInt() ?: 0,
    imageUrl = null, //TODO()
    categoryId = categoryId,
    isFavorite = storedFavorites.contains(itemId),
    tags = tags
)

