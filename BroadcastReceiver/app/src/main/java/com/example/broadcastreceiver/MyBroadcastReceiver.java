package com.example.broadcastreceiver;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final int JOB_ID = 123;

    @Override
    public void onReceive(Context context, Intent intent) {
        // Trigger the JobScheduler when the system event occurs
        scheduleJob(context);
    }

    private void scheduleJob(Context context) {
        // Create a JobInfo.Builder to configure the job parameters
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, new ComponentName(context, MyJobService.class));

        // Set additional job parameters as needed
        // builder.setRequiredNetworkType(...)
         builder.setRequiresCharging(true);
        // builder.setPeriodic(...)

        // Schedule the job using the JobScheduler
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());
    }
}

