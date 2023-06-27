package com.example.dbview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecycleAdapter adapter;
    ArrayList<User> list = new ArrayList<>();


    //    Image dynamic call generator
    int[] imageResources = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n};
    String[] names = {" Aliisa Simona ", " Yonaguska Arjun ", " Tybalt Wardell", "Phyllis Dodson", "Fahad Francis", "Charlotte Boyer","Abu Fletcher" , "Maizie Riley",  "Jon Moss", " Abbey Wagner",  "Ariana Spencer",  "Peggy Walton"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
       adapter = new RecycleAdapter(getApplicationContext(), list);
        recyclerview.setAdapter(adapter);

        initFromDb();

        Button add_btn = findViewById(R.id.add_btn);
        Button delete_btn = findViewById(R.id.delete_btn);
        Button pop_btn = findViewById(R.id.pop_btn);

        add_btn.setOnClickListener(v -> {
            addTodb();
            adapter.notifyDataSetChanged();
            Log.d("my msg", "List size is: " + list.size());

        });

        delete_btn.setOnClickListener(v->{
            list.clear();
            deleteAlldb();
            Log.d("my msg", "All data deleted!");
            adapter.notifyDataSetChanged();
        });

        pop_btn.setOnClickListener(v->{
            deleteUser();
            initFromDb();
            adapter.notifyDataSetChanged();
        });

    }

    public int randomGenerator(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

//    public void initData(int howtManytoGenerate) {
//        for(int i = 0; i < howtManytoGenerate; i++){
//            list.add(new User(imageResources[randomGenerator(0, imageResources.length)], names[randomGenerator(0,names.length)] ));
//        }
//    }

    public void initFromDb(){
        list.clear();
        myDatabase db = myDatabase.getInstance(getApplicationContext());
        List<User> users = db.userDao().fetchAll();
        for(User user : users){
            list.add(new User(user.getImage(), user.getName(), user.getUid()));
        }
    }

    public void addTodb(){
        myDatabase db = myDatabase.getInstance(getApplicationContext());
        User user = new User(imageResources[randomGenerator(0, imageResources.length)], names[randomGenerator(0,names.length)] );
        db.userDao().insert(user);
        initFromDb();
        Log.d("sss", "Data inserted!");

    }
    public void deleteAlldb(){
        myDatabase db = myDatabase.getInstance(getApplicationContext());
        db.userDao().deleteAll();
    }

    public void deleteUser(){
        myDatabase db = myDatabase.getInstance(getApplicationContext());

        if(list != null ){

            if(list.size() == 0){
                Toast.makeText(getApplicationContext(), "Already empty!", Toast.LENGTH_SHORT).show();
            }
            else{
                db.userDao().deleteUser(list.get(list.size()-1).getUid());
                Log.d("my msg", "Last UID is: "+ list.get(list.size()-1).getUid());
            }
        }


    }



}