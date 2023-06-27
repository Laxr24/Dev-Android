package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class secondActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn1 = findViewById(R.id.firstView);
        btn2 = findViewById(R.id.secondView);
        btn3 = findViewById(R.id.thirdView);
        back = findViewById(R.id.back);

        fragmentLoader(new AFragment(), 1);

        btn1.setOnClickListener(v->{
            fragmentLoader(new AFragment(), 0);
        });
        btn2.setOnClickListener(v->{
            fragmentLoader(new BFragment(), 0);
        });
        btn3.setOnClickListener(v->{
            fragmentLoader(new CFragment(), 0);
        });

        back.setOnClickListener(v->{
            Intent home = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(home);
        });
    }

    public void fragmentLoader(Fragment fragment, int flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(flag != 0)
            ft.add(R.id.container, fragment);
        else
            ft.replace(R.id.container, fragment);

        ft.commit();
    }
}