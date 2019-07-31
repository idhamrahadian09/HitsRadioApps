package com.apps.idhamrahadian.hitsradioapps.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.apps.idhamrahadian.hitsradioapps.R;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private static final int PERM_REQ_CODE = 23;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findViewById(R.id.v_blob_btn).setOnClickListener(this);
        findViewById(R.id.v_blast_btn).setOnClickListener(this);
        findViewById(R.id.v_wave_btn).setOnClickListener(this);
        findViewById(R.id.v_bar_btn).setOnClickListener(this);
        findViewById(R.id.v_stream_btn).setOnClickListener(this);
        findViewById(R.id.v_circle_line_btn).setOnClickListener(this);
        findViewById(R.id.v_hifi_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.v_bar_btn:
                if (checkAudioPermission())
                    launchSpikyWaveActivity();
                else
                    requestAudioPermission();
                break;
            case R.id.v_stream_btn:
                if (checkAudioPermission())
                    launchMusicStreamActivity();
                else
                    requestAudioPermission();
                break;


        }
    }

    private boolean checkAudioPermission() {
        return ActivityCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestAudioPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERM_REQ_CODE);
    }


    private void launchSpikyWaveActivity() {
        Intent intent = new Intent(Main2Activity.this, BarActivity.class);
        startActivity(intent);
    }
    private void launchMusicStreamActivity() {
        Intent intent = new Intent(Main2Activity.this, MusicStreamActivity.class);
        startActivity(intent);
    }


}
