package com.mandarinkafe.mandarin.menu.data.dto

data class IkkoMenuRequest(
    val externalMenuId: String,
    val organizationIds: List<String>,
)