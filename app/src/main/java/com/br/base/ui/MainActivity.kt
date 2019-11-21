package com.br.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.br.base.api.http.AuthHttp
import com.br.base.util.Util
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.br.base.R.layout.activity_main)

        enter.setOnClickListener{
            AuthHttp(this).login(email.text.toString(), password.text.toString())
        }

        exit.setOnClickListener{ Util.setApiToken(null)}
    }
}