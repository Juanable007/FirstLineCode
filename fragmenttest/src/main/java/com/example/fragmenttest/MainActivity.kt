package com.example.fragmenttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_left_fragement.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            replaceFragment(AnotherRightFragment())
        }
        replaceFragment(Right_Fragement())


        val fragment = supportFragmentManager.findFragmentById(R.id.leftFrag)
        val value = leftFrag as Left_Fragement
        val activity = value.activity
    }

    //将fragment添加到返回栈中,可以返回当前fragment
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.rightLayout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    /* private fun replaceFragment(fragment: Fragment) {
         val fragmentManager = supportFragmentManager
         val transaction = fragmentManager.beginTransaction()
         transaction.replace(R.id.rightLayout, fragment)
         transaction.commit()
     }*/


}