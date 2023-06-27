package MyServices;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;

import androidx.annotation.Nullable;

import com.example.service.MainActivity;

public class MyBoundService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                MainActivity.log("Service running");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        MainActivity.log("Service stopped");
        stopSelf();
        super.onDestroy();
    }
}
