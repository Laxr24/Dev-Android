package com.example.workmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Utility.notifyUser(context, "Alarm"+System.currentTimeMillis(), "Alarm generate "+System.currentTimeMillis());
        Utility.stopRepeatingAlerm();
    }
}
