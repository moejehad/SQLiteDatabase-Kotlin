package com.example.assignment2

import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    val notificationManager by lazy {
        MyNotificationManager(this)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signup_login.setOnClickListener {
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }


        button.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            intent.putExtra("username", username.text.toString())
            intent.putExtra("password", password.text.toString())
            startForegroundService(intent)
        }

    }
}