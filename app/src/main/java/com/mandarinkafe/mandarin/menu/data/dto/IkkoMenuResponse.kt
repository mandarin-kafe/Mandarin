package com.mandarinkafe.mandarin.menu.data.dto

data class IkkoMenuResponse(
    val description: String,
    val id: Int,
    val itemCategories: List<ItemCategory>,
    val name: String
)

data class ItemCategory(
    val buttonImageUrl: String,
    val description: String,
    val headerImageUrl: String,
    val id: String,
    val items: List<Item>,
    val name: String
)

data class Item(
    val allergenGroups: List<AllergenGroup>,
    val description: String,
    val itemId: String,
    val itemSizes: List<ItemSize>,
    val modifierSchemaId: String,
    val name: String,
    val orderItemType: String,
    val sku: String,
    val taxCategory: TaxCategory
)

data class ItemX(
    val allergenGroups: List<AllergenGroup>,
    val buttonImage: String,
    val description: String,
    val itemId: String,
    val name: String,
    val nutritionPerHundredGrams: NutritionPerHundredGramsX,
    val portionWeightGrams: Int,
    val prices: List<PriceX>,
    val restrictions: RestrictionsX,
    val sku: String,
    val tags: List<Tag>
)

data class AllergenGroup(
    val code: String,
    val id: String,
    val name: String
)

data class ItemSize(
    val buttonImageCroppedUrl: List<String>,
    val buttonImageUrl: String,
    val isDefault: Boolean,
    val itemModifierGroups: List<ItemModifierGroup>,
    val nutritionPerHundredGrams: NutritionPerHundredGramsX,
    val portionWeightGrams: Int,
    val prices: List<PriceX>,
    val sizeCode: String,
    val sizeId: String,
    val sizeName: String,
    val sku: String
)

data class TaxCategory(
    val id: String,
    val name: String,
    val percentage: Int
)

data class ItemModifierGroup(
    val canBeDivided: Boolean,
    val childModifiersHaveMinMaxRestrictions: Boolean,
    val description: String,
    val itemGroupId: String,
    val items: List<ItemX>,
    val name: String,
    val restrictions: RestrictionsX,
    val sku: String
)

class NutritionPerHundredGramsX

data class PriceX(
    val organizationId: String,
    val price: Double
)



data class RestrictionsX(
    val byDefault: Int,
    val freeQuantity: Int,
    val maxQuantity: Int,
    val minQuantity: Int
)

data class Tag(
    val id: String,
    val name: String
)