package com.example.workmanager;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "Notify ID";
    private static final String WORK_NAME = "my custom work";
    private static final String TAG = "my";
    WorkManager wm;

    @SuppressLint("InvalidPeriodicWorkRequestInterval")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Button button = findViewById(R.id.workerButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(MyWorker.class, 3, TimeUnit.MINUTES).build();
                WorkManager.getInstance(getApplicationContext()).enqueueUniquePeriodicWork("myUn", ExistingPeriodicWorkPolicy.KEEP, workRequest);
            }
        });

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}