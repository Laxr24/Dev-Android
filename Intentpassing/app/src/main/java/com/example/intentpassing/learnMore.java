package com.example.intentpassing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class learnMore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_more);

        Intent getData = getIntent();
        TextView msg = findViewById(R.id.msg);
        Button backBtn = findViewById(R.id.backbtn);


        msg.setText(getData.getStringExtra("name"));

        Log.d("custom", getData.getStringExtra("name"));

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "Learning finished ! ❌", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}