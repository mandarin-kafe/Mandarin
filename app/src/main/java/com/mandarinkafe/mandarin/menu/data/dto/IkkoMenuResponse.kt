package com.mandarinkafe.mandarin.menu.data.dto

data class IkkoMenuResponse(
    val correlationId: String,
    val groups: List<Any>,
    val productCategories: List<Any>,
    val products: List<Any>,
    val revision: Long,
    val sizes: List<Any>
): Response()