package com.example.broadcastreceiver;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class MyJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        // Perform your background task here
        for (int i = 0; i < 10; i++) {
            try{
                Thread.sleep(1000);
                Log.i("my", "onStartJob: "+i);
            }catch (Exception e){

            }
        }
        // Return true if the task is still running, otherwise return false if it's completed
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        // Called when the job is prematurely stopped (e.g., due to constraints no longer being met)
        // Return true to reschedule the job, false otherwise
        return false;
    }
}
