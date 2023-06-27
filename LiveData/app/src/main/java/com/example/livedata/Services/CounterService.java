package com.example.livedata.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;

import androidx.annotation.Nullable;

import com.example.livedata.Tools.Tools;
import com.example.livedata.ViewModel.HomeViewModel;

public class CounterService extends Service {
    public static Thread childThread;
    public static boolean enableThread = true;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        enableThread = true;
        Tools.log("Service started");
        int range = Integer.valueOf(intent.getStringExtra("range"));
        childThread = new Thread(new Runnable() {
            @Override
            public void run() {
                    for (int i = range; i >= 0; i--) {
                        if(enableThread == false){
                            new Handler(Looper.getMainLooper()).post(()->{
                                HomeViewModel.c_num.setValue(0);
                            });
                            return;
                        }
                        if(i==0){
                            Tools.setNotification(getApplicationContext(), "Countdown stopped, total counts "+range, "Times up!", false);
                            stopSelf();
                        }
                        Tools.log(i+" " + Thread.currentThread().getId());
                        int finalI = i;
                        new Handler(Looper.getMainLooper()).post(()->{
                            HomeViewModel.c_num.setValue(finalI);
                        });
                        SystemClock.sleep(400);
                    }
            }
        });
        childThread.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Tools.log("Service stopped!");
        enableThread = false;
        super.onDestroy();
    }
}
