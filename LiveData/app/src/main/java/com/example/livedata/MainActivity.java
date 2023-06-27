package com.example.livedata;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.livedata.Services.CounterService;
import com.example.livedata.Tools.Tools;
import com.example.livedata.ViewModel.HomeViewModel;

public class MainActivity extends AppCompatActivity {

    Button startBtn, stopBtn;
    TextView counterTxt, randomTxt;
    EditText rangeInput;
    HomeViewModel homeViewModel;
    private static final String NOTIFICATION_CHANNEL = "Basic Test Notification";
    private static final int NOTIFICATION_ID_BASIC_TEST = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initiating UI elements
        initiateUiElements();
        Tools.log("Thread " + Thread.currentThread().getName());

        // Instantiate ViewModelProvider
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // Live Data observer

        HomeViewModel.r_num.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                randomTxt.setText(integer.toString());
            }
        });
        HomeViewModel.c_num.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                counterTxt.setText(integer.toString());
            }
        });

        // Start service with button
        startBtn.setOnClickListener(v -> {
            Intent service = new Intent(this, CounterService.class);
            service.putExtra("range", rangeInput.getText().toString().equals("") ? "10" : rangeInput.getText().toString());
            startService(service);
            rangeInput.setText("");
        });
        // Start service with button
        stopBtn.setOnClickListener(v -> {
            Intent service = new Intent(this, CounterService.class);
            stopService(service);
        });

    }


    public void initiateUiElements() {
        Tools.log("UI elements initiated");
        counterTxt = findViewById(R.id.counterTxt);
        randomTxt = findViewById(R.id.randomTxt);
        startBtn = findViewById(R.id.testBtn);
        stopBtn = findViewById(R.id.stopBtn);
        rangeInput = findViewById(R.id.rangeInput);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Want to quit?")
                .setCancelable(true)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onDestroy();
                    }
                });
        alert.show();

    }
}