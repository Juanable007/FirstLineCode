package com.example.activitytest1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_forth.*

class ForthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forth)

        button1.setOnClickListener {

            startActivity(Intent(this,FrageMentLayoutActivity::class.java))


        }
    }
}