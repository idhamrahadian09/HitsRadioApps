package com.apps.idhamrahadian.hitsradioapps.view;

import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.apps.idhamrahadian.hitsradioapps.R;

import java.io.IOException;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    /* Inisialisasi yang berguna untuk streaming radio */
    private String url_radio = "http://cloudstreaming.mramedia.com:8003/live";
    private ProgressBar playSeekBar;
    private TextView tvRadioUrl;
    private Button buttonPlay;
    private Button buttonStopPlay;
    private MediaPlayer player;

    /* Inisialisasi yang berguna untuk setting Toolbar dan DrawerLayout */
    DrawerLayout mDrawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle drawerToggle;

    /* Dipanggil ketika Activity yang pertama dibuat */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /* Untuk keperluan setting Toolbar dan DrawerLayout */
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null ){
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        }
        mDrawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        /* Kita tambahkan listener */
        mDrawerLayout.addDrawerListener(drawerToggle);
        setupNavDrawer(navigationView);


        /* Untuk keperluan streaming */
        initializeUIElements();
        initializeMediaPlayer();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(
                this,
                mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    private void setupNavDrawer(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener((new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_gallery:
                        fragment = new GalleryFragment();
                        break;
                        default:
                            fragment = new HomeFragment();
                }

                /* Gunakan fragment manager untuk mengatur dan mengganti fragment yang sesuai */
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

                /* Setelah terpilih, kita cek pilihan dan set judul toolbar sesuai menu */
                item.setChecked(true);
                setTitle(item.getTitle());
                mDrawerLayout.closeDrawers();

                return true;
            }
        }));
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                mDrawerLayout.openDrawer(Gravity.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
}
