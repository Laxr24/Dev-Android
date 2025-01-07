package com.example.workmanager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.concurrent.TimeUnit;

public class MyWork extends Worker {
    public MyWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try{
            Thread.sleep(4000);
            Utility.notifyUser(getApplicationContext(), "Update successfully", "Server update was successful. This notification is to inform you.");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        MyWork.scheduleWork(getApplicationContext());
        return Result.success();
    }

    public static void scheduleWork(Context context){
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(MyWork.class, 4, TimeUnit.SECONDS)
                .build();
        WorkManager.getInstance(context).enqueueUniquePeriodicWork("MainWork", ExistingPeriodicWorkPolicy.UPDATE, workRequest);
    }
}
