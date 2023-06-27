package com.example.fragmentapp;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;

public class CounterService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(()->{
            Log.i("msg", "Service Thread"+Thread.currentThread().getName());
            for (int i = 0; i < 10; i++) {
                if(i==9){
                    stopSelf();
                }
                DataModel.counter.postValue(Long.valueOf(i*1000));
                Log.i("msg", "Counting: "+i+" Thread: "+Thread.currentThread().getName());
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        return START_NOT_STICKY;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("msg", "Service destroyed");
    }
}
