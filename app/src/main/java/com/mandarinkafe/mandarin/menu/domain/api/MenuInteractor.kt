package com.mandarinkafe.mandarin.menu.domain.api
import com.mandarinkafe.mandarin.menu.data.dto.ItemCategory
import kotlinx.coroutines.flow.Flow

interface MenuInteractor {
    fun getMenu(): Flow<Pair<ArrayList<ItemCategory>?, String?>>
}