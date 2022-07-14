package com.example.databasetest

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 2)
        val createDatabase: Button = findViewById(R.id.createDatabase)

        createDatabase.setOnClickListener {
            println(" createDatabase.setOnClickListener 执行")
//            val db = dbHelper.writableDatabase
//            db.execSQL("insert  into Book values ('2','zhangrui','99999','888','java核心技术')")

        }
        addData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                // 开始组装第一条数据
                put("name", "The Da Vinci Code")
                put("author", "Dan Brown")
                put("pages", 454)
                put("price", 16.96)
            }
            db.insert(
                "Book",
                null,
                values1
            ) // 插入第一条数据
            val values2 = ContentValues().apply {
                // 开始组装第二条数据
                put("name", "The Lost Symbol")
                put("author", "Dan Brown")
                put("pages", 510)
                put("price", 19.95)
            }
            db.insert("Book", null, values2)

            println("成功插入输入--------")

//            db.execSQL("insert into Book (id , author, price, pages, name) values ('99','张三', '99999', '8888', 'java核心技术2');")
//            db.execSQL("insert into Book (id, author, price, pages, name) values ('100','张五', '99999', '88888', 'java核心技术3');")
            // 插入第二条数据
        }
        updateData.setOnClickListener {
            val writableDatabase = dbHelper.writableDatabase
            val value1 = ContentValues().apply {
                // 开始组装第二条数据

                put("price", 99999)
            }
            writableDatabase.update(
                "Book",
                value1,
                "author=? and pages =?",
                arrayOf("Dan Brown", "454")
            )
            println("ending")
        }

        deleteData.setOnClickListener {

            println("删除数据执行")

            val writableDatabase = dbHelper.writableDatabase

            writableDatabase.delete("Book", "id=?", arrayOf("2"))

        }
        queryData.setOnClickListener {

            val writableDatabase = dbHelper.writableDatabase
            val query = writableDatabase.query(
                "Book", arrayOf("name", "price"), null, null, null, null,
                null
            )

            if (query.moveToFirst()) {

                do { // 遍历Cursor对象，取出数据并打印
                    val name = query.getString(query.getColumnIndex("name"))
//                    val author = query.getString(query.getColumnIndex("author"))
//                    val pages = query.getInt(query.getColumnIndex("pages"))
                    val price = query.getDouble(query.getColumnIndex("price"))
                    Log.d("MainActivity", "book name is $name")
//                    Log.d("MainActivity", "book author is $author")
//                    Log.d("MainActivity", "book pages is $pages")
                    Log.d("MainActivity", "book price is $price")

                } while (query.moveToNext())

            }
            val cursor = writableDatabase.rawQuery("select * from Book", null)
            println(cursor.toString())
            query.close()
            println(query)

        }

        replaceData.setOnClickListener {
            Log.d("MainActivity: ", "替换数据按钮点击事件")
            val db = dbHelper.writableDatabase

            db.beginTransaction() // 开启事务

            try {

                db.delete("Book", null, null)

                if (true) { // 手动抛出一个异常，让事务失败
                    throw NullPointerException()
                }
                val values = ContentValues().apply {
                    put(
                        "name",
                        "Game of Thrones"
                    )
                    put("author", "George Martin")
                    put("pages", 720)
                    put("price", 20.85)
                }
                db.insert(
                    "Book",
                    null,
                    values
                )
                db.setTransactionSuccessful() // 事务已经执行成功
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction() // 结束事务
            }

        }
    }


}