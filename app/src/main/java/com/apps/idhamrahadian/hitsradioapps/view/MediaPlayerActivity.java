package com.apps.idhamrahadian.hitsradioapps.view;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.apps.idhamrahadian.hitsradioapps.R;

import java.io.IOException;

public class MediaPlayerActivity extends AppCompatActivity implements View.OnClickListener {

    /* Untuk keperluan streaming Radio */
    String url_radio = "http://hits.unikom.ac.id:9996/;listen.pls?sid=1";
    MediaPlayer player;
    private Button btnPlay;
    private Button btnPause;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*         Untuk keperluan streaming radio */
        initializeUIElements();

        initializeMediaPlayer();

    }

    private void initializeUIElements() {
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);

        btnPause = (Button) findViewById(R.id.btnPause);
        btnPlay.setOnClickListener(this);

    }

    public void onClick(View v){
        if (v == btnPlay){
            startPlaying();
        } else if (v == btnPause){
            stopPlaying();
        }
    }

    private void stopPlaying() {
        if (player.isPlaying()){
            player.stop();
            player.release();
            initializeMediaPlayer();
        }
        btnPlay.setEnabled(true);
        btnPause.setEnabled(false);

    }

    private void initializeMediaPlayer() {
        player = new MediaPlayer();
        try {
            player.setDataSource(url_radio);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        } catch (IllegalStateException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekBar.setIndeterminate(false);
                seekBar.setSecondaryProgress(100);
                Log.i("Buffering", "" + percent);


            }
        });

    }

    private void startPlaying() {
        btnPause.setEnabled(true);
        btnPlay.setEnabled(false);
        player.prepareAsync();
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                player.start();
            }
        });
    }
}
