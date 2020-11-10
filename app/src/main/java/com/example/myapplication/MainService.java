package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void startService(View view) {
        startService(
        new Intent(
                this,
                BackgroundTaskService.class
        )
 );
    }
    public void stopService(View view) {
        stopService(
                new Intent(
                        this,
                        BackgroundTaskService.class
                )
        );
    }
}
