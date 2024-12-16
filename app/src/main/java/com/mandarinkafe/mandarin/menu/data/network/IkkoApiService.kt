package com.mandarinkafe.mandarin.menu.data.network
import com.mandarinkafe.mandarin.menu.data.dto.IkkoAuthRequest
import com.mandarinkafe.mandarin.menu.data.dto.IkkoAuthResponse
import com.mandarinkafe.mandarin.menu.data.dto.IkkoMenuIdResponse
import com.mandarinkafe.mandarin.menu.data.dto.IkkoMenuOldRequest
import com.mandarinkafe.mandarin.menu.data.dto.IkkoMenuOldResponse
import com.mandarinkafe.mandarin.menu.data.dto.IkkoMenuRequest
import com.mandarinkafe.mandarin.menu.data.dto.IkkoMenuResponse
import com.mandarinkafe.mandarin.menu.data.dto.IkkoOrganizationsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface IkkoApiService {
    @POST("/api/1/access_token")
    suspend fun authenticate(@Body request: IkkoAuthRequest): IkkoAuthResponse

    @POST("/api/1/organizations")
    suspend fun getOrganizations(
        @Header("Authorization") token: String,
        @Body body: Any = emptyMap<String, Any>() // Пустое тело запроса
    ): Call<IkkoOrganizationsResponse>

    @POST("api/2/menu")
    suspend fun getMenuId(
        @Header("Authorization") token: String,
         ): IkkoMenuIdResponse

    @POST("api/2/menu/")
    suspend  fun getMenuById(
        @Header("Authorization") token: String,
        @Body body: IkkoMenuRequest
    ): IkkoMenuResponse

    @POST("/api/1/nomenclature")
    suspend fun getMenuOld(
        @Header("Authorization") token: String,
        @Body body: IkkoMenuOldRequest
    ): IkkoMenuOldResponse

}


