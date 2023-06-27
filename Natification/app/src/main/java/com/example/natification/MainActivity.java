package com.example.natification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(v -> {

            Intent screen = new Intent(getApplicationContext(), learnMore.class);
            TaskStackBuilder myStack = TaskStackBuilder.create(getApplicationContext());
            myStack.addNextIntentWithParentStack(screen);

            PendingIntent takeUser = myStack.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                Notification notification = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    notification = new Notification.Builder(getApplicationContext())
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Boredef"+" texted you!")
                            .setSubText("Quick action needed ❌")
                            .setAutoCancel(true)
                            .setAllowSystemGeneratedContextualActions(true)
                            .setContentIntent(takeUser)
                            .setChannelId("Basic channel")
                            .build();
                }
                nm.createNotificationChannel(new NotificationChannel("Basic channel", "Basic", NotificationManager.IMPORTANCE_HIGH));
                nm.notify(100,notification );
//                    startActivity(screen);
            }
            else{
                Notification notification = new Notification.Builder(getApplicationContext())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Boredef"+" texted you!")
                        .setSubText("Quick action needed ❌")
                        .setAutoCancel(true)
                        .setContentIntent(takeUser)
                        .build();
                nm.notify(100,notification );
//                    startActivity(screen);
            }


        });


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder exitDialog = new AlertDialog.Builder(this);
        exitDialog.setCancelable(true);
        exitDialog.setTitle("Exit");
        exitDialog.setMessage("Are you sure?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.super.onBackPressed();
            }
        });

        exitDialog.show();
    }
}