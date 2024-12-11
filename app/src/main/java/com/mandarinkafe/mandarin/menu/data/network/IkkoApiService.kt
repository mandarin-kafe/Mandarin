package com.mandarinkafe.mandarin.menu.data.network
import com.mandarinkafe.mandarin.menu.data.dto.IkkoAuthRequest
import com.mandarinkafe.mandarin.menu.data.dto.IkkoAuthResponse
import com.mandarinkafe.mandarin.menu.data.dto.IkkoMenuRequest
import com.mandarinkafe.mandarin.menu.data.dto.IkkoMenuResponse
import com.mandarinkafe.mandarin.menu.data.dto.IkkoOrganizationsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface IkkoApiService {
    @POST("/api/1/access_token")
    fun authenticate(@Body request: IkkoAuthRequest): Call<IkkoAuthResponse>

    @POST("/api/1/organizations")
    fun getOrganizations(
        @Header("Authorization") token: String,
        @Body body: Any = emptyMap<String, Any>() // Пустое тело запроса
    ): Call<IkkoOrganizationsResponse>

    @POST("/api/1/nomenclature")
    fun getMenu(
        @Header("Authorization") token: String,
        @Body body: IkkoMenuRequest
    ): Call<IkkoMenuResponse>

}


