package com.example.asus.lesgo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {
    private static int SPLASH_SCREEN_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );
        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                setContentView( R.layout.activity_splash );
                Intent intent = new Intent( Splash.this, Home.class );
                startActivity( intent );
            }
        }, SPLASH_SCREEN_OUT );
    }
}
