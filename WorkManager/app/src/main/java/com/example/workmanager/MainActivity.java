package com.example.workmanager;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "Notify ID";
    private static final String WORK_NAME = "my custom work";
    private static final String TAG = "my";
    WorkManager wm;

    @SuppressLint("InvalidPeriodicWorkRequestInterval")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wm = WorkManager.getInstance(this);


        new Thread(() -> {
            try {
                getThreadID("");
                wm.beginUniqueWork(WORK_NAME, ExistingWorkPolicy.REPLACE, OneTimeWorkRequest.from(MyBgWork.class)).enqueue();

            } catch (Exception e) {
                Toast.makeText(this, "Exception to schedule work!", Toast.LENGTH_SHORT).show();
            }
        }).start();

    }

//    static void makeStatusNotification(String message, Context context, String title) {
//
//        // Make a channel if necessary
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            // Create the NotificationChannel, but only on API 26+ because
//            // the NotificationChannel class is new and not in the support library
//            CharSequence name = "Constants.VERBOSE_NOTIFICATION_CHANNEL_NAME";
//            String description = "Constants.VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION";
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel =
//                    new NotificationChannel(CHANNEL_ID, name, importance);
//            channel.setDescription(description);
//
//            // Add the channel
//            NotificationManager notificationManager =
//                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//            if (notificationManager != null) {
//                notificationManager.createNotificationChannel(channel);
//            }
//        }
//
//        // Create the notification
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setContentTitle(title)
//                .setContentText(message)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setVibrate(new long[0]);
//
//        // Show the notification
//        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        NotificationManagerCompat.from(context).notify(10, builder.build());
//    }

    static void makeStatusPendingNotification(Context context, String msg, String tilteText) {
        Intent getToHome = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, getToHome, PendingIntent.FLAG_IMMUTABLE);

        // Make a channel if necessary
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            CharSequence name = "General updates";
            String description = "Shows general updates with status, contents";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel =
                    new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Add the channel
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        // Create the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(tilteText)
                .setContentText(msg)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[0]);
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        NotificationManagerCompat.from(context).notify(11, builder.build());
    }
    public static void getThreadID(String additional_msg){
        Log.i(TAG, "Main thread 📍: "+Thread.currentThread().getId()+" "+additional_msg);
    }

    @Override
    protected void onDestroy() {
        getThreadID("Destroyed app! ❌");
        super.onDestroy();

    }
}