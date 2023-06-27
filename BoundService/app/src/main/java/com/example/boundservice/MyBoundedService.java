package com.example.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyBoundedService extends Service {
    public MyBinder binder = new MyBinder();
    private String date = "";
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public String getTime(){
        doTHeBackgroundWork();
        return date;
    }
    public class MyBinder extends Binder{
        MyBoundedService getServiceInstance(){
            return MyBoundedService.this;
        }
    }

    private void doTHeBackgroundWork(){
        new Thread(()->{
            Log.i("msg", "doTHeBackgroundWork on Thread: "+Thread.currentThread().getId());
            SimpleDateFormat formatter;
            formatter = new SimpleDateFormat("hh:mm:ss:ms");
            date =  formatter.format(new Date());
        }).start();
    }

}