package com.br.base.api.response

import com.br.base.model.Data
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginResponse (

    @SerializedName("success")
    @Expose
    var sucess: Boolean,

    @SerializedName("data")
    @Expose
    var data : Data,

    @Expose
    @SerializedName("message")
    var message : String
)