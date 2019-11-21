package com.br.base.api.request

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import okhttp3.RequestBody
import com.br.base.api.auth.Authenticated
import com.br.base.api.services.ApiServices
import okhttp3.MediaType
import timber.log.Timber


class ApiRequest : Authenticated() {

    private val JSON = MediaType.parse("application/json")

    val services: ApiServices
        get() = retrofit!!.create(ApiServices::class.java)

    init {
        setupRetrofit()
    }

    /* Request Body JSON Object */
    fun setBody(`object`: JsonObject): RequestBody {
        return RequestBody.create(JSON, `object`.toString())
    }

    /* With show body JSON Object */
    fun setBody(`object`: JsonObject, isShowBody: Boolean): RequestBody {
        if (isShowBody) showBody(`object`)
        return RequestBody.create(JSON, `object`.toString())
    }

    /* Request Body JSON Array */
    fun setBody(array: JsonArray): RequestBody {
        return RequestBody.create(JSON, array.toString())
    }

    /* With show body JSON Array */
    fun setBody(array: JsonArray, isShowBody: Boolean): RequestBody {
        if (isShowBody) showBody(array)
        return RequestBody.create(JSON, array.toString())
    }

    /* Show body JSON Object */
    private fun showBody(`object`: JsonObject) {
        Timber.tag("RequestBody-d").d(`object`.toString())
    }

    /* Show body JSON Array */
    private fun showBody(`object`: JsonArray) {
        Timber.tag("RequestBody-d").d(`object`.toString())
    }
}