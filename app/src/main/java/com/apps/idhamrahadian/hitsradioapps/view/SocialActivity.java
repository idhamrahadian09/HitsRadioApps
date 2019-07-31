package com.apps.idhamrahadian.hitsradioapps.view;

import android.app.ProgressDialog;
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
import android.widget.EditText;
import android.widget.Toast;

import com.apps.idhamrahadian.hitsradioapps.R;
import com.apps.idhamrahadian.hitsradioapps.api.ApiEndPoint;
import com.apps.idhamrahadian.hitsradioapps.api.ApiService;
import com.apps.idhamrahadian.hitsradioapps.model.ResponseModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SocialActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar = null;

    private ProgressDialog progress;

    @BindView(R.id.edt_nama)
    EditText edtNama;
    @BindView(R.id.edt_kekuatan)
    EditText edtKekuatan;
    @BindView(R.id.edt_tahun)
    EditText edtTahun;
    @BindView(R.id.edt_day)
    EditText edtDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
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

        ButterKnife.bind(this);
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {

            case R.id.nav_mediaPlayer:
                Intent mp = new Intent(SocialActivity.this, MediaPlayerActivity.class);
                startActivity(mp);
                break;

            case R.id.nav_favouriteSong:
                Intent i = new Intent(SocialActivity.this, FavouriteActivity.class);
                startActivity(i);
                break;

            case R.id.nav_songReq:
                Intent sr = new Intent(SocialActivity.this, SongReqActivity.class);
                startActivity(sr);
                break;

            case R.id.nav_gallery:
                Intent gl = new Intent(SocialActivity.this, GalleryActivity.class);
                startActivity(gl);
                break;

            case R.id.nav_events:
                Intent ev = new Intent(SocialActivity.this, EventActivity.class);
                startActivity(ev);
                break;

            case R.id.nav_youtube:
                Intent yt = new Intent(SocialActivity.this, YoutubeActivity.class);
                startActivity(yt);
                break;

            case R.id.nav_alarmClock:
                Intent ac = new Intent(SocialActivity.this, AlarmActivity.class);
                startActivity(ac);
                break;

            case R.id.nav_schedule:
                Intent sc = new Intent(SocialActivity.this, ScheduleActivity.class);
                startActivity(sc);
                break;

            case R.id.nav_contact:
                Intent cc = new Intent(SocialActivity.this, ContactActivity.class);
                startActivity(cc);
                break;

            case R.id.nav_socialMedia:
                break;

            case R.id.nav_about:
                Intent ab = new Intent(SocialActivity.this, AboutActivity.class);
                startActivity(ab);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.btn_tambah)
    void tambah() {
        // progress dialog
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading ...");
        progress.show();

        //Get value to variabel
        String nama = edtNama.getText().toString();
        String kekuatan = edtKekuatan.getText().toString();
        String tahun = edtTahun.getText().toString();
        String day = edtDay.getText().toString();


        //Declare Retrofit
        ApiService api = ApiEndPoint.getClient().create(ApiService.class);

        Call<ResponseModel> addProgramResponseModelCall = api.postProgram(nama, kekuatan, tahun,day);
        addProgramResponseModelCall.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                String statusCode = response.body().getStatusCode();
                String message = response.body().getMessage();

                progress.dismiss();
                edtNama.setText("");
                edtKekuatan.setText("");
                edtTahun.setText("");
                edtDay.setText("");

                if (statusCode.equals("200")) {
                    Toast.makeText(SocialActivity.this, message, Toast.LENGTH_SHORT).show();
                } else if (statusCode.equals("404")) {
                    Toast.makeText(SocialActivity.this, message, Toast.LENGTH_SHORT).show();
                } else if (statusCode.equals("500")) {
                    Toast.makeText(SocialActivity.this, message, Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(SocialActivity.this, "Oops, your connection is WONGKY! ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btn_lihat)
    void lihat() {

        Intent i = new Intent(this, ScheduleActivity.class);
        startActivity(i);
    }
}
