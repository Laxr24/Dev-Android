package com.example.workmanager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyBgWork extends Worker {
    Context context;
    private final int counter = 0;
    private final int target = 20;

    public MyBgWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    public Result doWork() {
//        try{
//            new Thread(()->{
//                for (int i = 0; i < 10; i++) {
//                    try{
//                        Thread.sleep(3000);
//                    }catch (Exception e){
//
//                    }
//                    Log.i("my", "doWork: "+i);                }
//            }).start();
//            return Result.success();
//        }catch (Exception e){
//            return Result.failure();
//        }

//        for (int i = 1; i < 6; i++) {
//            try{
//                Thread.sleep(5000);
//            }catch (Exception e){
//
//            }
//            MainActivity.makeStatusPendingNotification(getApplicationContext(),"Done with "+i+" times", "Tap to go home");
//            Log.i("my", "Working: "+i);
//        }

        try {
            MainActivity.getThreadID("10 min test started!");
            Thread.sleep((1000 * 60) * 15);
            MainActivity.makeStatusPendingNotification(getApplicationContext(), "Test passed", "Result");
        } catch (Exception e) {

        }
        MainActivity.getThreadID("Work request done!");
        return Result.success();

    }
}