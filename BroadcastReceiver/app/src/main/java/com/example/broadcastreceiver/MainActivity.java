package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler(Looper.getMainLooper()).post(()->{
            try {
                MyBroadcastReceiver receiver = new MyBroadcastReceiver();
                IntentFilter filter = new IntentFilter("android.intent.action.SOME_SYSTEM_EVENT");
                Context context = getApplicationContext();
                context.registerReceiver(receiver, filter);

            }catch (Exception e){

            }
        });
    }
}