package com.example.databasetest

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(val context: Context, val name: String, val version: Int) :
    SQLiteOpenHelper(context, name, null, version) {


    private val createBook = "create table Book (" +
            " id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text)"

    private val createCategory =
        "create table Category (" + "id integer primary key autoincrement," +
                "category_name text," +
                "category_code integer)"


    override fun onCreate(db: SQLiteDatabase?) {
//数据库创建的时候执行
        if (db != null) {
            db.execSQL(createBook)
            db.execSQL(createCategory)
        }
        println("----------------------------------------------------------")
        Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show()

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //数据库更新升级的时候执行
        if (db != null) {
            db.execSQL("drop table if exists Book ")

            db.execSQL("drop table if exists Category")
        }
        onCreate(db)

    }


}