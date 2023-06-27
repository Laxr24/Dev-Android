package com.example.apiwithvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int latest = 0;
    int prevFlag = 1;
     TextView prevState;
     TextView nextID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView title = findViewById(R.id.title);
        final TextView des = findViewById(R.id.description);
        final Button nextBtn = findViewById(R.id.next);
        final Button prevBtn = findViewById(R.id.prev);
        final Button deleteBtn = findViewById(R.id.deleteBtn);
        prevState = findViewById(R.id.prevFlag);
        nextID = findViewById(R.id.nextId);


        deleteBtn.setOnClickListener(v->{
            MyDbHandler db = MyDbHandler.getDB(getApplicationContext());
            db.databaseAccessor().deleteAll();
            title.setText("No title!");
            des.setText("No description!");
        });

        initWithDb(title, des);

        Log.d("mymsg", "Current id is: "+latest);

        nextBtn.setOnClickListener(v-> fetchData(title, des, latest+1));


        prevBtn.setOnClickListener(v->{
            if(latest != 0){
                MyDbHandler db = MyDbHandler.getDB(getApplicationContext());
                Device device = db.databaseAccessor().getAll().get(prevFlag);
                title.setText(device.getTitle());
                des.setText(device.getDescription());
            }
        });
    }

    public void fetchData (TextView titleView, TextView descriptionView, int id) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://dummyjson.com/products/"+id;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    // Display the first 500 characters of the response string.
                    try {
                        JSONObject obj = new JSONObject(response);
                        String title = obj.get("title").toString();
                        String description = obj.get("description").toString();
                        MyDbHandler db = MyDbHandler.getDB(getApplicationContext());
                        Device device = new Device(title, description, id);
                        db.databaseAccessor().setDevice(device);
                        initWithDb(titleView, descriptionView);

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                        throw new RuntimeException(e);
                    }

                }, error -> {
                    Toast.makeText(getApplicationContext(), "Network error!", Toast.LENGTH_SHORT).show();
                    Log.d("My msg", "Error occurred ! ");
                });

        stringRequest.setTag("Title request");
        queue.add(stringRequest);
    }

    public void initWithDb(TextView title, TextView description){
        MyDbHandler db = MyDbHandler.getDB(getApplicationContext());
        ArrayList<Device> allData = (ArrayList<Device>) db.databaseAccessor().getAll();
        int lastItem = allData.size();

        if(lastItem > 0){
            latest = allData.get(lastItem-1).getLastUid();
            prevFlag = latest -1;
            Log.d("mymsg", "Title: "+allData.get(lastItem-1).getTitle());
            title.setText(allData.get(lastItem-1).getTitle());
            description.setText(allData.get(lastItem-1).getDescription());
        }
        else{
            Toast.makeText(getApplicationContext(), "No data in database!", Toast.LENGTH_SHORT).show();
            Log.d("mymsg", "No data found! ");
        }

        prevState.setText("Previous cursor: "+prevFlag);
        nextID.setText("Next request ID: "+ String.valueOf(latest+1));


    }

}