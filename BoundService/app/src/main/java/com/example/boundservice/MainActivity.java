package com.example.boundservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView msg;
    Button bind, unbind, getTime;
    MyBoundedService myBoundedService = new MyBoundedService();
    boolean isBound = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msg = findViewById(R.id.msg);
        bind = findViewById(R.id.bind);
        unbind = findViewById(R.id.unbind);
        getTime = findViewById(R.id.getTime);

        getTime.setOnClickListener(v->{
            getServiceData(msg);
        });
        bind.setOnClickListener(v->{
            if(!isBound){
                bindService(new Intent(this, MyBoundedService.class),serviceConnection, BIND_AUTO_CREATE);
            }
        });
        unbind.setOnClickListener(v->{
            if(isBound){
                unbindService(serviceConnection);
            }
        });



    }

    private void getServiceData(TextView textView){
        textView.setText(myBoundedService.getTime());
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBoundedService.MyBinder binder = (MyBoundedService.MyBinder) service;
            myBoundedService = binder.getServiceInstance();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };
}