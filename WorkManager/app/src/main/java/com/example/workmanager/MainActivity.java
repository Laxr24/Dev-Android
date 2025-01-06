package com.example.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.myButton);

        btn.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Background task started...", Toast.LENGTH_LONG).show();
            WorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class)
                    .setInitialDelay(1, TimeUnit.MINUTES).build();
            WorkManager.getInstance(MainActivity.this).enqueue(request);
        });
    }
}