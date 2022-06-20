package com.example.activitytest1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.first_layout.*

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
//        val button1: Button = findViewById(R.id.button1)
//        toast提示工具使用
        button1.setOnClickListener { Toast.makeText(this, "button1被搞了", Toast.LENGTH_LONG).show() }


        /*   button1.setOnClickListener {
               println("我背搞了被报告了b")
           }*/

        /*关闭Activity页面*/
//        button1.setOnClickListener { finish() }
        button1.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)


        return true


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "add菜单按钮被点击了", Toast.LENGTH_LONG).show()
            R.id.remove_item -> Toast.makeText(this, "remove菜单按钮被点击了", Toast.LENGTH_LONG).show()
        }
        return true
    }

}