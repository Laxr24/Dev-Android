package com.example.intentpassing;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button learnBtn;
    EditText nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        learnBtn = findViewById(R.id.learnBtn);
        nameInput = findViewById(R.id.nameInput);
        learnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(),learnMore.class);
                nextScreen.putExtra("name", nameInput.getText().toString());
                startActivity(nextScreen);
            }
        });
    }

    @Override
    protected void onPause() {
        Toast.makeText(this, "Main activity on Pause", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    protected void onResume() {
        nameInput = findViewById(R.id.nameInput);
        nameInput.setText("");
        Toast.makeText(this, "Welcome back", Toast.LENGTH_SHORT).show();
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Do not quit app!", Toast.LENGTH_SHORT).show();
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "Main destroyed! ", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}