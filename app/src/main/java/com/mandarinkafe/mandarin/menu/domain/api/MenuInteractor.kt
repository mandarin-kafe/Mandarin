package com.mandarinkafe.mandarin.menu.domain.api
import com.mandarinkafe.mandarin.menu.domain.models.ItemCategory
import kotlinx.coroutines.flow.Flow

interface MenuInteractor {
    fun getMenu(): Flow<Pair<List<ItemCategory>?, String?>>
}