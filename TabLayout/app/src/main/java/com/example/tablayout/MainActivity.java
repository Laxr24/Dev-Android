package com.example.tablayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tab ;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tab = findViewById(R.id.tabber);
        viewPager = findViewById(R.id.viewPager);
        FragmentManager fm = getSupportFragmentManager();

        FragmentMyAppAdapter adapter = new FragmentMyAppAdapter(fm);
        viewPager.setAdapter(adapter);

        tab.setupWithViewPager(viewPager);
    }
}