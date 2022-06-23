package com.example.activitytest1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CancellationSignal
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_forth.*
import kotlinx.android.synthetic.main.first_layout.*
import kotlinx.android.synthetic.main.first_layout.button1
import java.util.function.Consumer

class FirstActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("FirstActivity", this.toString())
        Log.d("FirstActivity", "Task id is $taskId") //打印任务id
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
//        val button1: Button = findViewById(R.id.button1)
//        toast提示工具使用
//        button1.setOnClickListener { Toast.makeText(this, "button1被搞了", Toast.LENGTH_LONG).show() }

        /*   button1.setOnClickListener {
               println("我背搞了被报告了b")
           }*/
        //关闭Activity页面
        //button1.setOnClickListener { finish() }
        //显式intent

        /*button1.setOnClickListener {
           /* 第一个参数Context要求提供一个启动Activity的上下 文；第二个参数Class用于指定想要启动的目标Activity，
            通过这个构造函数就可以构建出 Intent的“意图”。 那么接下来我们应该怎么使用这个Intent呢？
            Activity类中提供了一个 startActivity()方法，专门用于启动Activity，它接收一个Intent参数，这里我们将构建好
            的Intent传入startActivity()方法就可以启动目标Activity了。*/
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }*/
//        隐式intent
        /* button1.setOnClickListener {
             val intent = Intent("com.example.activitytest.ACTION_START")
             intent.addCategory("com.example.activitytest.MY_CATEGORY")

             startActivity(intent)
         }
 */
//        调用浏览器
        /*   button1.setOnClickListener {
               val intent = Intent(Intent.ACTION_VIEW)
               intent.data = Uri.parse("https://www.baidu.com")
               startActivity(intent)
           }*/

//        调用电话程序
        button1.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        }
//        传递数据
        /*button1.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val data = "123131232131313123131 "
            intent.putExtra("extra_data", data)

            startActivity(intent)
        }*/

        //回传数据

        /* button1.setOnClickListener {
                    val intent = Intent(this, SecondActivity::class.java)
                    val data = "123131232131313123131 "
                    intent.putExtra("extra_data", data)

                    startActivityForResult(intent,1)
                }*/


//        active启动模式

//        button1.setOnClickListener {
//
//            val intent = Intent(this, SecondActivity::class.java)
//
//            startActivity(intent)
//        }



//        edittext点击事件传值
    /*    button1.setOnClickListener{
            val text = editText.text
            Toast.makeText(this,text, Toast.LENGTH_LONG).show()
        }
*/
        button1.setOnClickListener{
            val intent= Intent(this ,ForthActivity::class.java )
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println("方法执行")
        when (requestCode) {
            1 -> if (resultCode == RESULT_OK) {
                val returnedData = data?.getStringExtra("data_return")
                println("returned data is $returnedData")
            }
        }
        println("执行结束")
    }

}