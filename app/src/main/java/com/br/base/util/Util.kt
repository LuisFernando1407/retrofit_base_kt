package com.br.base.util

import android.graphics.drawable.ColorDrawable
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.Window
import android.text.Html
import android.content.SharedPreferences
import com.br.base.BaseRetrofitApplication
import com.br.base.R


object Util {
    fun loadingDialog(ctx: Context): Dialog {
        val loading = Dialog(ctx)
        loading.requestWindowFeature(Window.FEATURE_NO_TITLE)
        loading.setContentView(R.layout.dialog_loading)
        loading.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        loading.setCanceledOnTouchOutside(false)
        loading.setCancelable(false)
        return loading
    }

    fun alert(ctx: Context, title: String, message: String) {
        val alertDialogBuilder = android.app.AlertDialog.Builder(ctx)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(Html.fromHtml(message))
        alertDialog.show()
    }

    private fun getSessionPreferences(): SharedPreferences {
        val ctx = BaseRetrofitApplication.context
        return ctx!!.getSharedPreferences("SESSION_PREFERENCES", Context.MODE_PRIVATE)
    }

    fun getApiToken(): String? {
        return getSessionPreferences().getString("API_TOKEN", null)
    }

    fun setApiToken(token: String?) {
        val mPreferences = getSessionPreferences()
        val editor = mPreferences.edit()
        editor.putString("API_TOKEN", token)
        editor.apply()
    }

}