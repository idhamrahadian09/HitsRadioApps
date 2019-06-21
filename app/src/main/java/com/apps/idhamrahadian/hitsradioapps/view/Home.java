package com.apps.idhamrahadian.hitsradioapps.view;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.apps.idhamrahadian.hitsradioapps.R;

import java.io.IOException;

public class Home extends AppCompatActivity implements View.OnClickListener {

    private String url_radio = "http://cloudstreaming.mramedia.com:8003/live";
    private ProgressBar playSeekBar;

    private TextView tvRadioUrl;
    private Button buttonPlay;

    private Button buttonStopPlay;

    private MediaPlayer player;

//    Dipanggil ketika Activity yang pertama dibuat
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initializeUIElements();

        initializeMediaPlayer();
    }

    private void initializeUIElements() {

        playSeekBar = (ProgressBar) findViewById(R.id.progressBar1);
        playSeekBar.setMax(100);
        playSeekBar.setVisibility(View.INVISIBLE);
        playSeekBar.setIndeterminate(true);
        buttonPlay = (Button) findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(this);

        buttonStopPlay = (Button) findViewById(R.id.buttonStop);
        buttonStopPlay.setEnabled(false);
        buttonStopPlay.setOnClickListener(this);
        tvRadioUrl = (TextView) findViewById(R.id.textViewRadioUrl);
        tvRadioUrl.setText("Radio URL : "+url_radio);
    }

    public void onClick(View v){
        if (v == buttonPlay){
            startPlaying();
        } else if (v == buttonStopPlay){
            stopPlaying();
        }
    }

    private void stopPlaying() {
        if (player.isPlaying()){
            player.stop();
            player.release();
            initializeMediaPlayer();
        }
    }

    private void startPlaying() {
        buttonStopPlay.setEnabled(true);
        buttonPlay.setEnabled(false);

        playSeekBar.setVisibility(View.VISIBLE);

        player.prepareAsync();

        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                player.start();

            }
        });
    }

    private void initializeMediaPlayer(){
        player = new MediaPlayer();
        try{
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
                playSeekBar.setIndeterminate(false);
                playSeekBar.setSecondaryProgress(100);
                Log.i("Buffering", ""+ percent);
            }
        });
    }

    protected void onPause(){
        super.onPause();
        if (player.isPlaying()){
//            player.stop();
        }
    }



}
