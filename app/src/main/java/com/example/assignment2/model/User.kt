package com.example.assignment2.model

data class User (var id:Int , var name:String , var password:String , var email:String , var phone:String) {

    companion object{
        const val TABLE_NAME = "Users"
        const val COL_ID = "id"
        const val COL_NAME = "name"
        const val COL_PASSWORD = "password"
        const val COL_EMAIL = "email"
        const val COL_PHONE = "phone"

        const val TABLE_CREATE =
            "create table $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT , $COL_NAME TEXT NOT NULL , " +
                    "$COL_PASSWORD TEXT NOT NULL , $COL_EMAIL TEXT NOT NULL , $COL_PHONE TEXT NOT NULL)"
    }
}