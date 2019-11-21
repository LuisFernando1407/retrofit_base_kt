package com.br.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.br.base.R
import kotlinx.android.synthetic.main.activity_server_error.*
import kotlin.system.exitProcess

class ServerErrorActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_server_error)

        val bundle = intent.extras

        if (bundle != null) {
            txtMessage.text = bundle.getString("message")
        }
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
        android.os.Process.killProcess(android.os.Process.myPid())
        exitProcess(1)
    }
}