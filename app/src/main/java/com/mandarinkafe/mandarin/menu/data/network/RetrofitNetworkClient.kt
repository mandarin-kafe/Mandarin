package com.mandarinkafe.mandarin.menu.data.network

import android.content.Context
import com.mandarinkafe.mandarin.menu.data.dto.Response

class RetrofitNetworkClient(private val context: Context, private val apiService: IkkoApiService) : NetworkClient {

    override fun doRequest(dto: Any): Response {
        TODO("Not yet implemented")
    }
    //TODO Вынести сюда логику запросов из Fragment Delivery. Там она абсолютно временно :)

}