package com.mandarinkafe.mandarin.menu.data

import com.mandarinkafe.mandarin.menu.data.dto.MenuResponse
import com.mandarinkafe.mandarin.menu.data.network.NetworkClient
import com.mandarinkafe.mandarin.menu.domain.api.FavoritesRepository
import com.mandarinkafe.mandarin.menu.domain.api.MenuRepository
import com.mandarinkafe.mandarin.menu.domain.models.ItemCategory
import com.mandarinkafe.mandarin.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MenuRepositoryImpl(
    private val networkClient: NetworkClient,
    private val favoritesRepository: FavoritesRepository
) : MenuRepository {
    override fun getMenu(): Flow<Resource<List<ItemCategory>>> = flow {
        val response = networkClient.doRequest()
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету. "))
            }

            200 -> {
                val stored = favoritesRepository.getFavoriteIds()
                emit(Resource.Success(ArrayList((response as MenuResponse).itemCategories.map {
                    it.toDomain(
                        stored
                    )
                })))
            }

            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }
}