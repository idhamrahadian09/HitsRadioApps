package com.apps.idhamrahadian.hitsradioapps.view;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.apps.idhamrahadian.hitsradioapps.R;
import com.apps.idhamrahadian.hitsradioapps.utils.AudioPlayer;
import com.gauravk.audiovisualizer.visualizer.BarVisualizer;

public class BarActivity extends AppCompatActivity {

    private BarVisualizer mVisualizer;

    private AudioPlayer mAudioPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);

        mVisualizer = findViewById(R.id.bar);
        mAudioPlayer = new AudioPlayer();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startPlayingAudio(R.raw.sample);
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayingAudio();
    }

    private void startPlayingAudio(int resId) {
        mAudioPlayer.play(this, resId, new AudioPlayer.AudioPlayerEvent() {
            @Override
            public void onCompleted() {
                if (mVisualizer != null)
                    mVisualizer.hide();
            }
        });
        int audioSessionId = mAudioPlayer.getAudioSessionId();
        if (audioSessionId != -1)
            mVisualizer.setAudioSessionId(audioSessionId);
    }

    private void stopPlayingAudio() {
        if (mAudioPlayer != null)
            mAudioPlayer.stop();
        if (mVisualizer != null)
            mVisualizer.release();
    }

}
