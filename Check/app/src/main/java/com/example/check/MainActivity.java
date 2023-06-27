package com.example.check;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    CheckBox cb1, cb2;
    ImageView imageView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         cb1 = findViewById(R.id.checkBox);
         cb2 = findViewById(R.id.checkBox2);
         imageView = findViewById(R.id.imageView);
         imageView.setImageLevel(0);

        final String[] choice = {""};
        ((Button) findViewById(R.id.button)).setOnClickListener(v->{
            Intent intent = new Intent(this, MainActivity2.class );
            String data = choice[0];
            intent.putExtra("searchParam", data);
            Log.d("mymsg", "Put data: "+data);
            startActivity(intent);
        });
         cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if(isChecked) {
                     imageView.setImageLevel(1);
                     choice[0] = "Pizza";
                 }
                 else {
                     imageView.setImageLevel(0);
                     choice[0] = "";
                 }
             }
         });

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    imageView.setImageLevel(2);
                    choice[0] = "Burger";
                }
                else {
                    imageView.setImageLevel(0);
                    choice[0] = "";
                }
            }
        });

    }

}