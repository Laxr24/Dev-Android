package com.example.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyServiceOne extends Service {
    private static final String CHANNEL_ID = "Service notify channel";
    private static final int NOTIFICATION_ID = 100;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Intent showHome = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, showHome, PendingIntent.FLAG_IMMUTABLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                MainActivity.log("Service started on another thread ✅");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationManager nm = getSystemService(NotificationManager.class);
                    NotificationChannel nc = new NotificationChannel(CHANNEL_ID, "Service Notify!", NotificationManager.IMPORTANCE_DEFAULT);
                    nm.createNotificationChannel(nc);
                }

            }
        }).start();
        Notification notification = new NotificationCompat.Builder(getBaseContext(), CHANNEL_ID)
                .setContentTitle("Greetings!")
                .setContentText("You have been proofed!")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build();
        startForeground(NOTIFICATION_ID, notification);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MainActivity.log("Service about to end...");
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone ringTone = RingtoneManager.getRingtone(this, uri);
        ringTone.play();
    }
}