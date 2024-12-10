package com.mandarinkafe.mandarin.delivery

import IkkoApiService
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mandarinkafe.mandarin.databinding.FragmentDeliveryBinding
import com.mandarinkafe.mandarin.menu.data.dto.IkkoAuthRequest
import com.mandarinkafe.mandarin.menu.data.dto.IkkoAuthResponse
import com.mandarinkafe.mandarin.menu.data.dto.IkkoMenuRequest
import com.mandarinkafe.mandarin.menu.data.dto.IkkoMenuResponse
import com.mandarinkafe.mandarin.menu.data.dto.IkkoOrganizationsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DeliveryFragment : Fragment() {
    private var _binding: FragmentDeliveryBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authenticate()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeliveryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    val ikkoService: IkkoApiService = Retrofit.Builder()
        .baseUrl("https://api-ru.iiko.services")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(
            IkkoApiService::
            class.java
        )


    private fun authenticate() {
        ikkoService.authenticate(
            IkkoAuthRequest("3a901a233be740bea54bf0a38e1bfaa3")
        )
            .enqueue(object : Callback<IkkoAuthResponse> {
                override fun onResponse(
                    call: Call<IkkoAuthResponse>,
                    response: Response<IkkoAuthResponse>
                ) {
                    if (response.code() == 200) {
                        val token = response.body()?.token.toString()
                        binding.tvToken.text = token
                        Log.d("DEBUG", "token = $token")
                        getOrganizations(token)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Ошибка запрроса авторизации, Код ошибки: ${response.code()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<IkkoAuthResponse>, t: Throwable) {
                    Toast.makeText(
                        requireContext(),
                        "Ошибка запроса авторизации, попали в onFailure",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
    }


    private fun getOrganizations(token: String) {
        ikkoService.getOrganizations(
            ("Bearer $token")
        )
            .enqueue(object : Callback<IkkoOrganizationsResponse> {
                override fun onResponse(
                    call: Call<IkkoOrganizationsResponse>,
                    response: Response<IkkoOrganizationsResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val organizations = response.body()?.organizations
                        if (!organizations.isNullOrEmpty()) {

                            val cafeId =
                                organizations[0].id       // Извлекаем id первого кафе и сохраняем в переменную
                            binding.tvRests.text = cafeId
                            Log.d("DEBUG", "cafeId = $cafeId")

                            Toast.makeText(
                                requireContext(),
                                "ID кафе: $cafeId",
                                Toast.LENGTH_SHORT
                            ).show()

                            menuRequest(token, IkkoMenuRequest(organizationId = cafeId))


                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Список организаций пуст",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Ошибка получения данных. Код ошибки: ${response.code()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
private fun menuRequest(token: String, requestBody: IkkoMenuRequest) {
          ikkoService.getMenu(
            "Bearer $token", // Авторизация
            requestBody       // JSON-тело
        ).enqueue(object : Callback<IkkoMenuResponse> {
            override fun onResponse(
                call: Call<IkkoMenuResponse>,
                response: Response<IkkoMenuResponse>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        requireContext(),
                        "Запрос успешен: ${response.body()}",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.tvMenu.text = response.body().toString()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Ошибка выполнения запроса. Код ошибки: ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<IkkoMenuResponse>, t: Throwable) {
                Toast.makeText(
                    requireContext(),
                    "Ошибка выполнения запроса: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })


}

                override fun onFailure(p0: Call<IkkoOrganizationsResponse>, p1: Throwable) {
                    Toast.makeText(
                        requireContext(),
                        "Ошибка получение кода ресторана, попали в onFailure",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
    }


}




