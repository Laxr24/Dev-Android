package com.example.bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemReselectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottom_navigation_view = findViewById(R.id.bt_navigation);

//      Must come before default selector in this context!
        bottom_navigation_view.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.homeView){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragContainer, new AFragment())
                            .commit();

                }else if(item.getItemId() == R.id.about){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragContainer, new BFragment())
                            .commit();

                }
                else{
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragContainer, new CFragment())
                            .commit();

                }
                return true;
            }
        });
//      Initialize a default view in the beginning.
        bottom_navigation_view.setSelectedItemId(R.id.homeView);
    }


    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

    }
}