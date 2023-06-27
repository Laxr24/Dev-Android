package com.example.livedata.Tools;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.livedata.R;

public class Tools {
    private static final String TAG = "msg";
    private static final String NOTIFICATION_CHANNEL = "Basic Test Notification";
    private static final int NOTIFICATION_ID_BASIC_TEST = 10;


    public static void log(String msg) {
        Log.i(TAG, ":>> " + msg);
    }

    public static void setNotification(Context context, String msg, String titleText, boolean autoDismiss) {
        // Make a channel if necessary
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            CharSequence name = "Test Channel";
            String description = "This category notifies users about countdown finished status";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel =
                    new NotificationChannel(NOTIFICATION_CHANNEL, name, importance);
            channel.setDescription(description);

            // Add the channel
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            if (notificationManager != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationManager.createNotificationChannel(channel);
                }
            }
        }

        // Create the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL)
                .setSmallIcon(R.drawable.alarm)
                .setContentTitle(titleText)
                .setContentText(msg)
                .setAutoCancel(autoDismiss);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID_BASIC_TEST, builder.build());
    }

}
