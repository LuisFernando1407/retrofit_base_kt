package com.br.base.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data (
    @SerializedName("token")
    @Expose
    val token: String?,

    @SerializedName("user")
    @Expose
    val user: User?
)