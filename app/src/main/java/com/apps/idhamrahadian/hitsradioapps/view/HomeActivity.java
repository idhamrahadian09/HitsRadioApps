package com.apps.idhamrahadian.hitsradioapps.view;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;


import com.apps.idhamrahadian.hitsradioapps.MainActivity;
import com.apps.idhamrahadian.hitsradioapps.R;

import java.io.IOException;

public class HomeActivity extends AppCompatActivity {

/*    *//* Untuk keperluan streaming Radio *//*
    String url_radio = "http://hits.unikom.ac.id:9996/;listen.pls?sid=1";
    MediaPlayer player;
    private Button btnPlay;
    private Button btnPause;
    private SeekBar seekBar;*/

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

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();

        /* Untuk keperluan streaming radio */
/*        initializeUIElements();

        initializeMediaPlayer();*/

    }
/*
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
    }*/

    private ActionBarDrawerToggle setupDrawerToggle(){
        return new ActionBarDrawerToggle(
                this,
                mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    private void setupNavDrawer(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_favouriteSong:
                        fragment = new FavouriteFragment();
                        break;
                    case R.id.nav_songRequest:
                        fragment = new SongRequestFragment();
                        break;
                    case R.id.nav_gallery:
                        fragment = new GalleryFragment();
                        break;
                    case R.id.nav_socialMedia:
                        fragment = new SocialMedia();
                        break;
                    case R.id.nav_setting:
                        fragment = new AboutFragment();
                        break;
                    case R.id.nav_about:
                        fragment = new AboutFragment();
                        break;
                        default:
                            fragment = new AboutFragment();
                            break;


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
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                mDrawerLayout.openDrawer(Gravity.START);
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }


}
