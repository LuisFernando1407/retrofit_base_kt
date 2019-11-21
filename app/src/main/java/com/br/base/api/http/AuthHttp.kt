package com.br.base.api.http

import android.content.Context
import com.br.base.api.BaseCallbackApi
import com.br.base.api.request.ApiRequest
import com.br.base.api.response.LoginResponse
import com.br.base.util.Util
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

class AuthHttp(val context: Context) {

    private var request: ApiRequest = ApiRequest()

    fun login(email: String, pass: String) {
        val auth = JsonObject()
        auth.addProperty("email", email)
        auth.addProperty("password", pass)

        val callLogin = request.services.login(request.setBody(auth))

        callLogin.enqueue(object : BaseCallbackApi<LoginResponse>(context, true) {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                super.onResponse(call, response)

                if (response.isSuccessful) {
                    Util.setApiToken(response.body()?.data!!.token!!)
                    Timber.tag("USER-LOGGED").d(Gson().toJson(response.body()?.data!!.user))
                }
            }
        })
    }
}