package com.example.dataperformance

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val editText: TextView = findViewById(R.id.editText)

        val inputText = load()
        if (inputText.isNotEmpty()) {

            editText.setText(inputText)

            editText.setSelection(inputText.length)

            Toast.makeText(this, "Restoring succeeded", Toast.LENGTH_SHORT).show()
        }
        val sharedPreferences = getSharedPreferences("shareTest",0)
        val edit = sharedPreferences.edit()
        edit.putString("hello","world")
        edit.putInt("num",123)
        edit.commit()
        println("======================================")

    }

    override fun onDestroy() {
        super.onDestroy()
        val editText: TextView = findViewById(R.id.editText)
        val inputText = editText.text.toString()
        save(inputText)
    }

    private fun save(inputText: String) {
        println("++++++++++++++++++++++++++++")
        try {
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use { it.write(inputText) }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        println("-------------------------------")
    }

    private fun load(): String {

        val content = StringBuilder()
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(
                InputStreamReader(input)
            )
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return content.toString()

    }
}