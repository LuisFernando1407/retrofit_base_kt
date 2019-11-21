package com.br.base.api.auth

import com.br.base.api.constants.APIConstants
import com.br.base.util.Util
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


open class Authenticated {
    protected var retrofit: Retrofit? = null

    private val CONNECTION_TIMEOUT = 20 * 1000

    private val client = OkHttpClient.Builder().addInterceptor { chain ->
        var newRequest = chain.request()

        if (Util.getApiToken() != null) {
            newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + Util.getApiToken())
                .build()
        }

        chain.proceed(newRequest)
    }.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES).build()

    protected fun setupRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl(APIConstants.BASE)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}