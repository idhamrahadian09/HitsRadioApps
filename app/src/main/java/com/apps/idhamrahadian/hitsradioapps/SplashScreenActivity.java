package com.apps.idhamrahadian.hitsradioapps;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.apps.idhamrahadian.hitsradioapps.view.MediaPlayerActivity;


public class SplashScreenActivity extends AppCompatActivity {
    private static final int PERM_REQ_CODE = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        if (checkAudioPermission()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashScreenActivity.this, MediaPlayerActivity.class));
                    finish();
                }
            }, 4000);
        }
        else
            requestAudioPermission();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MediaPlayerActivity.class));
                finish();
            }
        }, 4000);


    }
    private boolean checkAudioPermission() {
        return ActivityCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestAudioPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERM_REQ_CODE);
    }



}
