package com.example.activitytest1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_frage_ment_layout.*

class FrageMentLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frage_ment_layout)


        button.setOnClickListener {
            startActivity(Intent(this,TopLayoutActivity::class.java))
        }
    }
}