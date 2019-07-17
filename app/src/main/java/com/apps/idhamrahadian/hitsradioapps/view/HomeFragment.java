package com.apps.idhamrahadian.hitsradioapps.view;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;


import com.apps.idhamrahadian.hitsradioapps.R;

import java.io.IOException;


public class HomeFragment extends Fragment implements OnClickListener{

    /* Untuk keperluan streaming Radio */
    String url_radio = "http://hits.unikom.ac.id:9996/;listen.pls?sid=1";
    MediaPlayer player;
    private Button btnPlay;
    private Button btnPause;
    private SeekBar seekBar;


    //    Dipanggil ketika activity pertama dibuat
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        /*         Untuk keperluan streaming radio */
/*        initializeUIElements();

        initializeMediaPlayer();*/


        return view;
    }

    private void initializeUIElements() {

        btnPlay = (Button) getView().findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);

        btnPause = (Button) getView().findViewById(R.id.btnPause);
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