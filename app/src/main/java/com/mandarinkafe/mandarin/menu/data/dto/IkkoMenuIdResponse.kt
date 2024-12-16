package com.mandarinkafe.mandarin.menu.data.dto

data class IkkoMenuIdResponse(
    val correlationId: String,
    val externalMenus: List<Menu>,

    )

data class Menu(
    val id: String,
    val name: String
)

