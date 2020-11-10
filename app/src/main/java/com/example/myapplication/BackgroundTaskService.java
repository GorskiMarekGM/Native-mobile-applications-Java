package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

public class BackgroundTaskService extends Service {
    private boolean looping;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        Toast.makeText(this, "Creating background task service...",
                Toast.LENGTH_LONG).show();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Starting background task service...",
                Toast.LENGTH_LONG).show();
        this.looping = true;
        Runnable runnable = new Runnable() {
            public void run() {
                int retryCounter = 0;
                while ((retryCounter <= 999) && (looping)) {
                    retryCounter++;
                    synchronized (this) {
                        try {
                            wait(2000);
                        } catch (Exception e) {
                            //handle exception
                        }
                    }
                    int finalRetryCounter = retryCounter;
                    new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(
                            BackgroundTaskService.this,
                            String.format(
                                    "Background task is running and counting:%s...",
                    finalRetryCounter
 ),
                    Toast.LENGTH_SHORT
 ).show());
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Toast.makeText(this, "Starting background task service finished...",
                Toast.LENGTH_LONG).show();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.looping = false;
        Toast.makeText(this, "Destroying background task service...",
                Toast.LENGTH_LONG).show();
    }
}
