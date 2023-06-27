package com.example.workmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_HEADSET_PLUG.equals(intent.getAction())){
            new Handler(Looper.getMainLooper()).post(()->{
                MainActivity.makeStatusPendingNotification(context, "From broadcast", "Device started");
            });
        }

    }
}
