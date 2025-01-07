package com.example.workmanager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.Telephony;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;

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


    public static void readSms(Context context) {
        ArrayList<String> smsList = new ArrayList<String>();
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(
                Telephony.Sms.CONTENT_URI,
                null,
                null,
                null,
                null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String address = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS));
                String body = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY));
                smsList.add("Sender: " + address + "\nMessage: " + body);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        int vals = smsList.size();
        Log.d("log", String.valueOf(smsList.get(smsList.size()-2)));
    }
}
