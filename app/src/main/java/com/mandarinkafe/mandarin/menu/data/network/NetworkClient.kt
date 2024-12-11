package com.mandarinkafe.mandarin.menu.data.network

import com.mandarinkafe.mandarin.menu.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response
}
