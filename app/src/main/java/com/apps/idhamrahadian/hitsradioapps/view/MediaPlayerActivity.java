package com.apps.idhamrahadian.hitsradioapps.view;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import com.apps.idhamrahadian.hitsradioapps.R;

import java.io.IOException;

public class MediaPlayerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    Button next;

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar = null;

    /* Untuk keperluan streaming Radio */
    String url_radio = "http://103.112.189.100:9996/;stream.nsv";
    MediaPlayer player;
    private Button btnPlay;
    private Button btnPause;



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

        /* Untuk keperluan streaming radio */
        initializeUIElements();

        initializeMediaPlayer();
/*
        *//*Untuk memperlancar menggunakan Intent*//*
        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentku = new Intent(MediaPlayerActivity.this, MediaPlayerActivity.class);
                startActivity(intentku);
            }
        });*/
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

    // Untuk Streaming Radio
    private void initializeUIElements() {


        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);

        btnPause = (Button) findViewById(R.id.btnPause);
        btnPause.setOnClickListener(this);


    }

    public void onClick(View v){
        if (v == btnPlay){
            startPlaying();
        } else if (v == btnPause){
            stopPlaying();
        }
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
        btnPlay.setVisibility(View.INVISIBLE);
        btnPause.setVisibility(View.VISIBLE);
    }

    private void stopPlaying() {
        if (player.isPlaying()){
            player.stop();
            player.release();
            initializeMediaPlayer();
        }
        btnPlay.setEnabled(true);
        btnPause.setEnabled(false);
        btnPlay.setVisibility(View.VISIBLE);
        btnPause.setVisibility(View.INVISIBLE);

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
/*        player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekBar.setIndeterminate(false);
                seekBar.setSecondaryProgress(100);
                Log.i("Buffering", "" + percent);
            }
        });*/

    }


}
