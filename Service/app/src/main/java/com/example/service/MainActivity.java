package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import MyServices.MyBoundService;

public class MainActivity extends AppCompatActivity {

    Button start, stop; TextView msg;
    private BroadcastReceiver chargerReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.startBtn);
        stop = findViewById(R.id.stopBtn);
        msg = findViewById(R.id.msg);

//        Intent serviceIntent = new Intent(this, MyServiceOne.class);
        Intent serviceIntent = new Intent(this, MyBoundService.class);

//        Broadcast receiver Dynamic
//        chargerReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {

//            }
//         };
//        registerReceiver(chargerReceiver, new IntentFilter(Intent.ACTION_POWER_CONNECTED));

        
        start.setOnClickListener(v->{
            log("S-Btn");
            startService(serviceIntent);
        });

        stop.setOnClickListener(v->{
            log("Stop-Btn");
            stopService(serviceIntent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(chargerReceiver);
    }

    public static void log(String msg){
        Log.i("mymsg", "Thread ID ▶: "+Thread.currentThread().getId()+" "+msg);
    }
}