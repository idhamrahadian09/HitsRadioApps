package com.apps.idhamrahadian.hitsradioapps.view;

import android.content.Intent;
import android.os.Bundle;
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

import com.apps.idhamrahadian.hitsradioapps.R;

public class EventActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
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
                Intent mp = new Intent(EventActivity.this, MediaPlayerActivity.class);
                startActivity(mp);
                finish();
                break;

            case R.id.nav_favouriteSong:
                Intent i = new Intent(EventActivity.this, FavouriteActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.nav_songReq:
                Intent sr = new Intent(EventActivity.this, SongReqActivity.class);
                startActivity(sr);
                finish();
                break;

            case R.id.nav_gallery:
                Intent gl = new Intent(EventActivity.this, GalleryActivity.class);
                startActivity(gl);
                finish();
                break;

            case R.id.nav_events:
                break;

            case R.id.nav_youtube:
                Intent yt = new Intent(EventActivity.this, YoutubeActivity.class);
                startActivity(yt);
                finish();
                break;

            case R.id.nav_alarmClock:
                Intent ac = new Intent(EventActivity.this, AlarmActivity.class);
                startActivity(ac);
                finish();
                break;

            case R.id.nav_schedule:
                Intent sc = new Intent(EventActivity.this, ScheduleActivity.class);
                startActivity(sc);
                finish();
                break;

            case R.id.nav_contact:
                Intent cc = new Intent(EventActivity.this, ContactActivity.class);
                startActivity(cc);
                finish();
                break;

            case R.id.nav_socialMedia:
                Intent sm = new Intent(EventActivity.this, SocialActivity.class);
                startActivity(sm);
                finish();
                break;

            case R.id.nav_about:
                Intent ab = new Intent(EventActivity.this, AboutActivity.class);
                startActivity(ab);
                finish();
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
