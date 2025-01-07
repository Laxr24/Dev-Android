package com.example.workmanager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import androidx.core.app.NotificationCompat;

public class Utility {
    public static void sendNotification(Context context, String title, String content){
        String channelID = "MYNOTIFICATION";

        NotificationManager nm = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            nm = context.getSystemService(NotificationManager.class);
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel nc = new NotificationChannel(channelID, "Main channel", NotificationManager.IMPORTANCE_HIGH);
            nm.createNotificationChannel(nc);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationManager.IMPORTANCE_HIGH);

        nm.notify(10, builder.build());

    }
}
