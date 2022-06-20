package com.example.activitytest1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.second_layout.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        println("second oncreate执行----")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)
        val ac = intent.getStringExtra("extra_data")
        println(ac)

        button2.setOnClickListener {
            val intent = Intent()

            intent.putExtra("data_return", "Hello FirstActivity")

            setResult(RESULT_OK, intent)

            finish()
        }
    }

    //返回键回调参数
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent()

        intent.putExtra("data_return", "Hello FirstActivity")

        setResult(RESULT_OK, intent)

    }

    override fun onStart() {
        super.onStart()
        println("second onstart执行_-------")
    }

}