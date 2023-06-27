package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import dbHandler.myDb;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_delete = findViewById(R.id.btn_delete);
        Button btn_fetch = findViewById(R.id.btn_fetch);

//        Delete all data from the table
        btn_delete.setOnClickListener(v->{
            new myDb(getApplicationContext()).deleteAlldata();
            Log.d("My msg", "Deleted all records from 'User' table!");
        });

//        On Long press Insert
        btn_delete.setOnLongClickListener(v->{
            dummyInsert();
            return true;
        });


//        Fetch Data on click listener
        btn_fetch.setOnClickListener(v->{
            LiveData<List<User>> users = new myDb(getApplicationContext()).fetchAll();
           Log.d("my msg", "value: "+ users.getValue().size());

        });



    }


    public void dummyInsert(){
        for(int i = 0; i <10; i++){
            new myDb(getApplicationContext()).insertData("Ralphael "+i);
        }
    }


}