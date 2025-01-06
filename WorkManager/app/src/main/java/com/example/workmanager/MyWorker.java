package com.example.workmanager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {
    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Utility.sendNotification(getApplicationContext(), "Background task done", "Your task is been done");
        return Result.success();
    }
}
