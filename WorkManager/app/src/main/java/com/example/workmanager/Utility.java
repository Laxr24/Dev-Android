package com.example.workmanager;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.Telephony;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Utility {

    private static PendingIntent pendingIntent;
    private static AlarmManager alarmManager;
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

    public static void setRepeatingTaskWithAlarm(Context context){
        alarmManager = context.getSystemService(AlarmManager.class);
        Intent intent = new Intent(context, AlarmBroadCast.class);
        pendingIntent = PendingIntent.getBroadcast(context, 200, intent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 5000, 5000, pendingIntent);
    }

    public static void stopRepeatingAlerm(){
        alarmManager.cancel(pendingIntent);
    }

    public static void fetchAPI(Context context, String url){
        RequestQueue queue = Volley.newRequestQueue(context);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("log","Response is: " + response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("log","Something went wrong");
            }
        });

        queue.add(stringRequest);

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
