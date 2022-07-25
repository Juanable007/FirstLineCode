package com.example.androidtreadtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView text;

    private static final int update_Text = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            System.out.println("handle message 执行");
            switch (msg.what) {
                case update_Text:
                    text.setText("Nice to  meet  you ");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button changeBtn = (Button) findViewById(R.id.changeTextBtn);

        text = (TextView) findViewById(R.id.textView);
        changeBtn.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        System.out.println("onlick");
        switch (v.getId()) {
            case R.id.changeTextBtn:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = update_Text;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            default:
                break;
        }

    }
}