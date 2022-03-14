package com.example.assignment2

import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast
import com.example.assignment2.db.DatabaseHelper

    class MyService() : Service() {


        var MyNotificationManager: MyNotificationManager? = null

        val db: DatabaseHelper by lazy {
            DatabaseHelper(this)
        }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        //Toast.makeText(this, "Service onCreate", Toast.LENGTH_SHORT).show()

        MyNotificationManager = MyNotificationManager(this)

        startForeground(
            MyNotificationManager!!.getNotificationID(),
            MyNotificationManager!!.getNotification(this)
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val password = intent!!.getStringExtra("password")
        val username = intent!!.getStringExtra("username")

        val intentLogin = Intent(this,Login::class.java)

        //Toast.makeText(this, "Service is Started", Toast.LENGTH_SHORT).show()
        val check = db.loginCheck(username.toString(), password.toString())
        if (check){
            MyNotificationManager?.showSmallNotification(1, "Login", "Login Succ", intentLogin)
        }else{
            MyNotificationManager?.showSmallNotification(1, "Login", "Login Failed", intentLogin)
        }
        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        //Toast.makeText(this, "Service is Destroy", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }
}
