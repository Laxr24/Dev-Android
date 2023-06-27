package com.example.expensemonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Screen_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_login);

        EditText password = findViewById(R.id.passWord);
        Button loginBtn = findViewById(R.id.btnLogin);

        final boolean[] isInternetAvailable = {false};

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.google.com";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        isInternetAvailable[0] = true;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

        loginBtn.setOnClickListener(v->{

            StringRequest runtimeReq = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            isInternetAvailable[0] = true;
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });

            queue.add(runtimeReq);

            String passWord = password.getText().toString();

            Log.d("mymsg", " pass: "+passWord);
            if(passWord.equals("0000")){
                if(isInternetAvailable[0] == true){
                    Intent home = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                    startActivity(home);
                }
                else{
                    Toast.makeText(this, "Please check internet connection!", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                password.setText("");
                Toast.makeText(this, "Invalid Login", Toast.LENGTH_SHORT).show();
            }
        });
    }
}