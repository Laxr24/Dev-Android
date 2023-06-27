package com.example.contentprovider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    Button getContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getContact = findViewById(R.id.getContact);

        getContact.setOnClickListener(v->{
            Toast.makeText(this, "Permission", Toast.LENGTH_SHORT).show();
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.READ_CONTACTS) ==
                    PackageManager.PERMISSION_GRANTED) {
                // You can use the API that requires the permission.
                Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
            } else {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.

            }

        });
    }
}