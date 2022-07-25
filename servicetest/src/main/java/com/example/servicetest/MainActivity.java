package com.example.servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyService.DownloadBinder downloadBinder  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startServiceBtn = (Button) findViewById(R.id.startServiceBtn);
        Button stopServiceBtn = (Button) findViewById(R.id.stopServiceBtn);

        startServiceBtn.setOnClickListener(this);
        stopServiceBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.startServiceBtn:
                Intent startServiceIntent = new Intent(this, MyService.class);
                startService(startServiceIntent);
                break;
            case R.id.stopServiceBtn:
                Intent stopServiceIntent = new Intent(this, MyService.class);
                stopService(stopServiceIntent);
                break;
            default:
                break;
        }
    }
}