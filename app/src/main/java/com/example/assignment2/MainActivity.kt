package com.example.assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.assignment2.db.DatabaseHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = DatabaseHelper(this)
        val MyNotificationManager = MyNotificationManager(this)
        val intentSignUp = Intent(this,MainActivity::class.java)

        button.setOnClickListener {
            if (username.text.isEmpty() || password.text.isEmpty() || email.text.isEmpty() || phone.text.isEmpty()) {
                MyNotificationManager?.showSmallNotification(1, "Sign Up", "Enter All Fields", intentSignUp)
            } else {
                if (db.checkUser(username.text.toString())){
                    MyNotificationManager?.showSmallNotification(1, "Sign Up", "This user is exist", intentSignUp)
                }else {
                    val add = db.insertUser(
                        username.text.toString(),
                        password.text.toString(),
                        email.text.toString(),
                        phone.text.toString()
                    )
                    if (add) {
                        val i = Intent(this,Login::class.java)
                        startActivity(i)
                        MyNotificationManager?.showSmallNotification(1, "Sign Up", "Added Successfully", i)
                        finish()
                    } else {
                        MyNotificationManager?.showSmallNotification(1, "Sign Up", "Added Failed", intentSignUp)
                    }
                }
            }
        }


        login_signup.setOnClickListener {
            val i = Intent(this, Login::class.java)
            startActivity(i)
        }


    }
}