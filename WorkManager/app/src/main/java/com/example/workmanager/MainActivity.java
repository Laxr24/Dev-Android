package com.example.workmanager;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        // Start code here kshantonu.top

        Button btn = findViewById(R.id.bgBtn);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText editText = findViewById(R.id.userTimeInput);
        TextView textView = findViewById(R.id.textView);

        AlarmManager alarmManager = getSystemService(AlarmManager.class);
        Intent intent = new Intent(this, AlarmBroadCast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 200, intent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);



//        Action on button click
        btn.setOnClickListener(v->{
            long userInput = Integer.parseInt(editText.getText().toString());
            Log.d("log", "onCreate: Button Clicked");
//            MyWork.scheduleWork(MainActivity.this);
            Utility.readSms(MainActivity.this);

//            Alam manager type execution
            Utility.setRepeatingTaskWithAlarm(this, userInput);
            Utility.fetchAPI(this, "https://socket.kshantonu.top/pay");
        });



    }
}