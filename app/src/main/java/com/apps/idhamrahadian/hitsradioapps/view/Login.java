package com.apps.idhamrahadian.hitsradioapps.view;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.apps.idhamrahadian.hitsradioapps.R;

public class Login extends AppCompatActivity {

    RelativeLayout relativeLayout;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        relativeLayout = (RelativeLayout) findViewById(R.id.loginlayout);
        animationDrawable = (AnimationDrawable) relativeLayout.getBackground();

        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
    }

    protected void onPause(){
        super.onPause();

        if(animationDrawable != null && animationDrawable.isRunning()){
            animationDrawable.stop();
        }
    }

    protected void onResume(){
        super.onResume();

        if (animationDrawable != null && !animationDrawable.isRunning()){
            animationDrawable.start();
        }
    }

    public void pindah(View view) {
        Intent intent = new Intent(Login.this, Home.class);
        startActivity(intent);
    }
}

