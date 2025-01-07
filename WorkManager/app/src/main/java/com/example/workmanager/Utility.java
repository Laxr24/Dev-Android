package com.example.workmanager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import androidx.core.app.NotificationCompat;

public class Utility {
    public static void notifyUser(Context context, String title, String content){
        String channelID = "mainEmmergencyChannel";
        NotificationManager nm;

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelID, "Emmergency Notification", NotificationManager.IMPORTANCE_HIGH);
            nm = context.getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);
            nm.notify(1001, builder.build());
        }



    }
}
