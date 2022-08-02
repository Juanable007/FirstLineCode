package com.example.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView response_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button send_request = (Button) findViewById(R.id.send_request);
        response_text = (TextView) findViewById(R.id.response_text);
        send_request.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_request:
                Log.d("1", "点击事件");
                sendRequestWithHttpURLConnection();
                break;
            default:
                break;
        }
    }

    private void showResponse(String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                在这里进行Ui操作 ,将结果显示到界面上
                Log.d("1", "设置UI");
                response_text.setText(response);
            }
        });
    }

    private void sendRequestWithHttpURLConnection() {
        //开启发送线程请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("1", "启动线程");
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://www.baidu.com");

                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream inputStream = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    StringBuffer stringBuffer = new StringBuffer();
                    while (true) {
                        if (!((line = reader.readLine()) != null)) break;

                        stringBuffer.append(line);
                    }
                    Log.d("1", "结束"+stringBuffer.toString());
                    showResponse(stringBuffer.toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }


}