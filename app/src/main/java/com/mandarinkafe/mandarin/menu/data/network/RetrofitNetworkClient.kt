package com.mandarinkafe.mandarin.menu.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.mandarinkafe.mandarin.menu.data.dto.AuthRequest
import com.mandarinkafe.mandarin.menu.data.dto.MenuRequest
import com.mandarinkafe.mandarin.menu.data.dto.OrganizationsRequest
import com.mandarinkafe.mandarin.menu.data.dto.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RetrofitNetworkClient(private val context: Context, private val ikkoService: IkkoApiService) :
    NetworkClient {

    private var apiLogin = "3a901a233be740bea54bf0a38e1bfaa3"
    private var token = ""
    private var organizationId = ""
    private var externalMenuId = ""


    override suspend fun doRequest(): Response {
        if (!isConnected()) {
            return Response().apply { resultCode = -1 }
        }
        return withContext(Dispatchers.IO) {
            try {
                val authResponse = ikkoService.authenticate(AuthRequest(apiLogin))
                token =
                    "Bearer ${authResponse.token}"
                Log.d("DEBUG IKKO API", "Токен получен: $token")

                val organizationsResponse = ikkoService.getOrganizations(
                    token = token,
                    body = OrganizationsRequest()
                )

                organizationId = organizationsResponse.organizations.firstOrNull()?.id
                    ?: throw IllegalStateException("No organization found")
                Log.d("DEBUG IKKO API", "Организация получена: $organizationId")

                val menuIdResponse = ikkoService.getMenuId(token)
                externalMenuId = menuIdResponse.externalMenus.firstOrNull()?.id
                    ?: throw IllegalStateException("Menu ID not found")
                Log.d("DEBUG IKKO API", "Menu ID получено: $externalMenuId")


                val menuResponse = ikkoService.getMenuById(
                    token = token,
                    body = MenuRequest(
                        externalMenuId = externalMenuId,
                        organizationIds = listOf(organizationId)
                    )
                )
                Log.d("DEBUG IKKO API", "Данные меню: ${menuResponse.itemCategories}")
                menuResponse.apply { resultCode = 200 }


            } catch (e: Throwable) {
                Response().apply { resultCode = 500 }
            }
        }
    }


    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }
}

//    private fun getOrganizations(token: String) {
//        ikkoService.getOrganizations(
//            ("Bearer $token")
//        )
//            .enqueue(object : Callback<IkkoOrganizationsResponse> {
//                override fun onResponse(
//                    call: Call<IkkoOrganizationsResponse>,
//                    response: Response<IkkoOrganizationsResponse>
//                ) {
//                    if (response.isSuccessful && response.body() != null) {
//                        val organizations = response.body()?.organizations
//                        if (!organizations.isNullOrEmpty()) {
//
//                            organizationId =
//                                organizations[0].id       // Извлекаем id первого кафе и сохраняем в переменную
//                            binding.tvRests.text = organizationId
//                            Log.d("DEBUG", "organizationId = $organizationId")
//                            menuRequest(token, IkkoMenuOldRequest(organizationId = organizationId))
//
//                        } else {
//                            Toast.makeText(
//                                requireContext(),
//                                "Список организаций пуст",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    } else {
//                        Toast.makeText(
//                            requireContext(),
//                            "Ошибка получения данных. Код ошибки: ${response.code()}",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//
//
//                override fun onFailure(call: Call<IkkoOrganizationsResponse>, t: Throwable) {
//                    Toast.makeText(
//                        requireContext(),
//                        "Ошибка получение кода ресторана, попали в onFailure. ${t.message}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//            })
//    }

//    private fun menuRequest(token: String, requestBody: IkkoMenuOldRequest) {
//        ikkoService.getMenuOld(
//            "Bearer $token", // Авторизация
//            requestBody       // JSON-тело
//        ).enqueue(object : Callback<IkkoMenuResponse> {
//            override fun onResponse(
//                call: Call<IkkoMenuResponse>,
//                response: Response<IkkoMenuResponse>
//            ) {
//                if (response.isSuccessful) {
//                    Toast.makeText(
//                        requireContext(),
//                        "Запрос меню успешен!",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    binding.tvMenu.text = response.body().toString()
//                } else {
//                    Toast.makeText(
//                        requireContext(),
//                        "Ошибка выполнения запроса. Код ошибки: ${response.code()}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    Log.d("API DEBUG", "${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<IkkoMenuResponse>, t: Throwable) {
//                Toast.makeText(
//                    requireContext(),
//                    "onFailure. Ошибка выполнения запроса: ${t.message}",
//                    Toast.LENGTH_SHORT
//                ).show()
//
//                Log.d("API DEBUG", "${t.message}")
//            }
//        })
//    }
