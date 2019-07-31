package com.apps.idhamrahadian.hitsradioapps.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.apps.idhamrahadian.hitsradioapps.R;
import com.apps.idhamrahadian.hitsradioapps.adapter.ProgramAdapter;
import com.apps.idhamrahadian.hitsradioapps.api.ApiEndPoint;
import com.apps.idhamrahadian.hitsradioapps.api.ApiService;
import com.apps.idhamrahadian.hitsradioapps.model.ProgramModel;
import com.apps.idhamrahadian.hitsradioapps.model.ResponseModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar = null;

    /*Untuk keperluan API*/
    private ProgramAdapter viewAdapter;
    private List<ProgramModel> mItems = new ArrayList<>();

    @BindView(R.id.recycleSchedule) RecyclerView recyclerView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
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

        /*Untuk keperluan menampilkan data dari API*/
        ButterKnife.bind(this);

        viewAdapter = new ProgramAdapter(this, mItems);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);

        loadDataProgram();
    }

    private void loadDataProgram() {
        //Declare Retrofit
        ApiService api = ApiEndPoint.getClient().create(ApiService.class);

        Call<ResponseModel> call = api.getProgram();
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String statusCode = response.body().getStatusCode();
                progressBar.setVisibility(View.GONE);
                if (statusCode.equals("200")) {
                    mItems = response.body().getResult();
                    viewAdapter = new ProgramAdapter(ScheduleActivity.this,mItems);
                    recyclerView.setAdapter(viewAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ScheduleActivity.this, "Oops, your connection is WONGKY! ", Toast.LENGTH_SHORT).show();

            }
        });
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
                Intent mp = new Intent(ScheduleActivity.this, MediaPlayerActivity.class);
                startActivity(mp);
                finish();
                break;

            case R.id.nav_favouriteSong:
                Intent i = new Intent(ScheduleActivity.this, FavouriteActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.nav_songReq:
                Intent sr = new Intent(ScheduleActivity.this, SongReqActivity.class);
                startActivity(sr);
                finish();
                break;

            case R.id.nav_gallery:
                Intent gl = new Intent(ScheduleActivity.this, GalleryActivity.class);
                startActivity(gl);
                finish();
                break;

            case R.id.nav_events:
                Intent ev = new Intent(ScheduleActivity.this, EventActivity.class);
                startActivity(ev);
                finish();
                break;

            case R.id.nav_youtube:
                Intent yt = new Intent(ScheduleActivity.this, YoutubeActivity.class);
                startActivity(yt);
                finish();
                break;

            case R.id.nav_alarmClock:
                Intent ac = new Intent(ScheduleActivity.this, AlarmActivity.class);
                startActivity(ac);
                finish();
                break;

            case R.id.nav_schedule:
                break;

            case R.id.nav_contact:
                Intent cc = new Intent(ScheduleActivity.this, ContactActivity.class);
                startActivity(cc);
                finish();
                break;

            case R.id.nav_socialMedia:
                Intent sm = new Intent(ScheduleActivity.this, SocialActivity.class);
                startActivity(sm);
                finish();
                break;

            case R.id.nav_about:
                Intent ab = new Intent(ScheduleActivity.this, AboutActivity.class);
                startActivity(ab);
                finish();
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}
