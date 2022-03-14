package com.example.assignment2.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.assignment2.model.User

class DatabaseHelper (context: Context) : SQLiteOpenHelper (context,DATABASE_NAME, null, DATABASE_VERSION) {

    private var db: SQLiteDatabase

    init {
        db = writableDatabase
    }

    companion object {
        const val DATABASE_NAME = "Assig2"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL(User.TABLE_CREATE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS ${User.TABLE_NAME}")
        onCreate(p0)
    }


    fun insertUser(name: String, password: String, email: String, phone: String ): Boolean {
        val cv = ContentValues()
        cv.put(User.COL_NAME, name)
        cv.put(User.COL_PASSWORD, password)
        cv.put(User.COL_EMAIL, email)
        cv.put(User.COL_PHONE, phone)
        return db.insert(User.TABLE_NAME, null, cv) > 0
    }


    fun checkUser(name: String): Boolean {
        val c = db.rawQuery(
            "select ${User.COL_NAME} from ${User.TABLE_NAME} where ${User.COL_NAME} = '$name' ",
            null
        )
        return c.count > 0
        c.close()
    }


    fun loginCheck(username: String, password: String): Boolean {
        val c = db.rawQuery(
            "select ${User.COL_NAME},${User.COL_PASSWORD} from ${User.TABLE_NAME} where ${User.COL_NAME} = '$username' " +
                    "and ${User.COL_PASSWORD} = '$password' ",
            null
        )
        return c.count > 0
    }

}