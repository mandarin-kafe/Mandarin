package com.mandarinkafe.mandarin.menu.data

import com.mandarinkafe.mandarin.menu.data.dto.ItemCategory
import com.mandarinkafe.mandarin.menu.data.dto.MenuResponse
import com.mandarinkafe.mandarin.menu.data.network.NetworkClient
import com.mandarinkafe.mandarin.menu.domain.api.FavoritesRepository
import com.mandarinkafe.mandarin.menu.domain.api.MenuRepository
import com.mandarinkafe.mandarin.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MenuRepositoryImpl(
    private val networkClient: NetworkClient,
    favoritesRepository: FavoritesRepository
) : MenuRepository {
    override fun getMenu(): Flow<Resource<ArrayList<ItemCategory>>> = flow {
        val response = networkClient.doRequest()
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету. "))
            }

            200 -> {
                // TODO обавить обработку в данные для domain + проверку на сохранение в избранных
                // val stored = favoritesRepository.getFavoriteIds()

                emit(Resource.Success(ArrayList((response as MenuResponse).itemCategories)))
            }

            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }
}