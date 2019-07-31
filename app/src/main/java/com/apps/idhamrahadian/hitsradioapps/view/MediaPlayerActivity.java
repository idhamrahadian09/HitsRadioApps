package com.apps.idhamrahadian.hitsradioapps.view;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;


import com.apps.idhamrahadian.hitsradioapps.R;

import com.gauravk.audiovisualizer.visualizer.BarVisualizer;

import java.io.IOException;


public class MediaPlayerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int PERM_REQ_CODE = 23;

    Button next;

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar = null;

    ImageButton imgButton;
    BarVisualizer mVisualizer;
    MediaPlayer mediaPlayer;

    String stream = "http://103.112.189.100:9996/;stream.nsv";

    boolean prepared = false;
    boolean started = false;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        imgButton = findViewById(R.id.playbtn);
        imgButton.setImageResource(R.drawable.playbtn);
        mVisualizer = findViewById(R.id.bar);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        int audioSessionId = mediaPlayer.getAudioSessionId();
        if (audioSessionId != AudioManager.ERROR) {
            mVisualizer.setAudioSessionId(mediaPlayer.getAudioSessionId());
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        new PlayerTask().execute(stream);

        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (started) {
                    started = false;
                    mediaPlayer.pause();
                    imgButton.setImageResource(R.drawable.playbtn);
                } else {
                    started = true;
                    mediaPlayer.start();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });
                    imgButton.setImageResource(R.drawable.pausebtn);
                }
            }
        });
    }

    class PlayerTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {

            try {
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.prepare();
                prepared = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (progressDialog.isShowing()) {
                progressDialog.cancel();
            }
            imgButton.setImageResource(R.drawable.playbtn);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (started) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (started) {
            //mediaPlayer.start();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
            mediaPlayer.prepareAsync();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (prepared) {
            mediaPlayer.release();
        }
        if (mVisualizer != null)
            mVisualizer.hide();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.media_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {

            case R.id.nav_mediaPlayer:
                break;

            case R.id.nav_favouriteSong:
                Intent i = new Intent(MediaPlayerActivity.this, FavouriteActivity.class);
                startActivity(i);
                break;

            case R.id.nav_songReq:
                Intent sr = new Intent(MediaPlayerActivity.this, SongReqActivity.class);
                startActivity(sr);
                break;

            case R.id.nav_gallery:
                Intent gl = new Intent(MediaPlayerActivity.this, GalleryActivity.class);
                startActivity(gl);
                break;

            case R.id.nav_events:
                Intent ev = new Intent(MediaPlayerActivity.this, EventActivity.class);
                startActivity(ev);
                break;

            case R.id.nav_youtube:
                Intent yt = new Intent(MediaPlayerActivity.this, YoutubeActivity.class);
                startActivity(yt);
                break;

            case R.id.nav_alarmClock:
                Intent ac = new Intent(MediaPlayerActivity.this, AlarmActivity.class);
                startActivity(ac);
                break;

            case R.id.nav_schedule:
                Intent sc = new Intent(MediaPlayerActivity.this, ScheduleActivity.class);
                startActivity(sc);
                break;

            case R.id.nav_contact:
                Intent cc = new Intent(MediaPlayerActivity.this, ContactActivity.class);
                startActivity(cc);
                break;

            case R.id.nav_socialMedia:
                Intent sm = new Intent(MediaPlayerActivity.this, SocialActivity.class);
                startActivity(sm);
                break;

            case R.id.nav_about:
                Intent ab = new Intent(MediaPlayerActivity.this, AboutActivity.class);
                startActivity(ab);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
