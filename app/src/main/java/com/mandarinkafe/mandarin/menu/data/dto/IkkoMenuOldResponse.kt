package com.mandarinkafe.mandarin.menu.data.dto

data class IkkoMenuOldResponse(
    val correlationId: String,
    val groups: List<Group>,
    val productCategories: List<ProductCategory>,
    val products: List<Product>,
    val revision: Long,
    val sizes: List<Size>
)

data class Group(
    val additionalInfo: String,
    val code: String,
    val description: String,
    val id: String,
    val imageLinks: List<String>,
    val isDeleted: Boolean,
    val isGroupModifier: Boolean,
    val isIncludedInMenu: Boolean,
    val name: String,
    val order: Int,
    val parentGroup: String,
    val seoDescription: String,
    val seoKeywords: String,
    val seoText: String,
    val seoTitle: String,
    val tags: List<String>
)

data class ProductCategory(
    val id: String,
    val isDeleted: Boolean,
    val name: String
)

data class Product(
    val additionalInfo: String,
    val canSetOpenPrice: Boolean,
    val carbohydratesAmount: Int,
    val carbohydratesFullAmount: Int,
    val code: String,
    val description: String,
    val doNotPrintInCheque: Boolean,
    val energyAmount: Int,
    val energyFullAmount: Int,
    val fatAmount: Int,
    val fatFullAmount: Int,
    val fullNameEnglish: String,
    val groupId: String,
    val groupModifiers: List<GroupModifier>,
    val id: String,
    val imageLinks: List<String>,
    val isDeleted: Boolean,
    val measureUnit: String,
    val modifierSchemaId: String,
    val modifierSchemaName: String,
    val modifiers: List<Modifier>,
    val name: String,
    val order: Int,
    val orderItemType: String,
    val parentGroup: String,
    val paymentSubject: String,
    val productCategoryId: String,
    val proteinsAmount: Int,
    val proteinsFullAmount: Int,
    val seoDescription: String,
    val seoKeywords: String,
    val seoText: String,
    val seoTitle: String,
    val sizePrices: List<SizePrice>,
    val splittable: Boolean,
    val tags: List<String>,
    val type: String,
    val useBalanceForSell: Boolean,
    val weight: Int
)

data class Size(
    val id: String,
    val isDefault: Boolean,
    val name: String,
    val priority: Int
)

data class GroupModifier(
    val childModifiers: List<ChildModifier>,
    val childModifiersHaveMinMaxRestrictions: Boolean,
    val defaultAmount: Int,
    val freeOfChargeAmount: Int,
    val hideIfDefaultAmount: Boolean,
    val id: String,
    val maxAmount: Int,
    val minAmount: Int,
    val required: Boolean,
    val splittable: Boolean
)

data class Modifier(
    val defaultAmount: Int,
    val freeOfChargeAmount: Int,
    val hideIfDefaultAmount: Boolean,
    val id: String,
    val maxAmount: Int,
    val minAmount: Int,
    val required: Boolean,
    val splittable: Boolean
)

data class SizePrice(
    val price: Price,
    val sizeId: String
)

data class ChildModifier(
    val defaultAmount: Int,
    val freeOfChargeAmount: Int,
    val hideIfDefaultAmount: Boolean,
    val id: String,
    val maxAmount: Int,
    val minAmount: Int,
    val required: Boolean,
    val splittable: Boolean
)

data class Price(
    val currentPrice: Int,
    val isIncludedInMenu: Boolean,
    val nextDatePrice: String,
    val nextIncludedInMenu: Boolean,
    val nextPrice: Int
)