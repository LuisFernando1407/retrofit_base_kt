package com.br.base.api

import android.app.Dialog
import android.content.Context
import com.br.base.util.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent
import com.br.base.ui.ServerErrorActivity
import org.apache.http.conn.ConnectTimeoutException
import java.net.ConnectException
import java.net.SocketTimeoutException


open class BaseCallbackApi<T>(val context: Context) : Callback<T> {

    private var dialog: Dialog = Util.loadingDialog(context)

    private var isLogin: Boolean = false

    constructor(context: Context, isLogin: Boolean) : this(context) {
        this.isLogin = isLogin
        OnStartLoading()
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        OnStopLoading()
        whenConnectTimeOut(t)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        OnStopLoading()
        alertStatusCode(response.code())
    }

    private fun OnStopLoading() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }

    private fun OnStartLoading() {
        dialog.show()
    }

    private fun alertStatusCode(statusCode: Int) {
        if (statusCode == 500) {
            Util.alert(
                context,
                "Falha",
                "Um erro interno ocorreu no servidor, por favor tente mais tarde."
            )
        } else if (statusCode == 404) {
            Util.alert(context, "Falha", "Servidor indisponível, por favor tente mais tarde.")
        } else if (statusCode == 401) {
            if (isLogin) {
                Util.alert(context, "Falha", "E-mail ou senha incorreto")
            } else {
                /* TODO: Refresh Token */
            }
        }
    }

    private fun whenConnectTimeOut(throwable: Throwable) {
        if (throwable is SocketTimeoutException || throwable is ConnectTimeoutException) {
            val intent = Intent(context, ServerErrorActivity::class.java)
            intent.putExtra(
                "message",
                "Tempo para conexão esgostado ou servidor indisponível, por favor tente mais tarde."
            )
            context.startActivity(intent)
        }

        if (throwable is ConnectException) {
            val intent = Intent(context, ServerErrorActivity::class.java)
            intent.putExtra(
                "message",
                "Verifique seu acesso a internet ou tente novamente mais tarde."
            )
            context.startActivity(intent)
        }
    }
}