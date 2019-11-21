package com.br.base.api.services

import com.br.base.api.constants.APIConstants
import com.br.base.api.response.LoginResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface ApiServices {
    @Headers(APIConstants.CONTENT_TYPE)
    @POST(APIConstants.LOGIN)
    fun login(@Body body: RequestBody): Call<LoginResponse>
}