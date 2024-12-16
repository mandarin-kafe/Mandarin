package com.mandarinkafe.mandarin.delivery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mandarinkafe.mandarin.databinding.FragmentDeliveryBinding
import com.mandarinkafe.mandarin.menu.data.dto.IkkoMenuOldRequest
import com.mandarinkafe.mandarin.menu.data.network.IkkoApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DeliveryFragment : Fragment() {
    private var _binding: FragmentDeliveryBinding? = null
    private val binding get() = requireNotNull(_binding) { "Binding wasn't initialized" }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeliveryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareApi()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    //TODO все сетевые запросы вынести в data слой menu, тут они временно для простоты отладки

    private val ikkoBaseUrl = "https://api-ru.iiko.services"
    private var token = ""
    private var organizationId = ""

    private val ikkoService: IkkoApiService = Retrofit.Builder()
        .baseUrl(ikkoBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(
            IkkoApiService::
            class.java
        )


    private fun prepareApi() {
        if (token.isNotEmpty() && organizationId.isNotEmpty()) {
            menuRequest(token, IkkoMenuOldRequest(organizationId = organizationId))
        } else
            authenticate()
    }


    private fun authenticate() {
//        ikkoService.authenticate(
//            IkkoAuthRequest("3a901a233be740bea54bf0a38e1bfaa3")
//        )
//            .enqueue(object : Callback<IkkoAuthResponse> {
//                override fun onResponse(
//                    call: Call<IkkoAuthResponse>,
//                    response: Response<IkkoAuthResponse>
//                ) {
//                    if (response.code() == 200) {
//                        token = response.body()?.token.toString()
//                        binding.tvToken.text = token
//                        Log.d("DEBUG", "token = $token")
//                        getOrganizations(token)
//                    } else {
//                        Toast.makeText(
//                            requireContext(),
//                            "Ошибка запроса авторизации, Код ошибки: ${response.code()}",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<IkkoAuthResponse>, t: Throwable) {
//                    Toast.makeText(
//                        requireContext(),
//                        "Ошибка запроса авторизации, попали в onFailure. ${t.message}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//            })
    }


    private fun getOrganizations(token: String) {
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
    }

    private fun menuRequest(token: String, requestBody: IkkoMenuOldRequest) {
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
    }
}





