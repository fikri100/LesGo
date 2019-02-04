package com.example.asus.lesgo;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    Button btnLoginGuru, btnLoginMurid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );


        btnLoginGuru = (Button)findViewById( R.id.loginGuru);
        btnLoginMurid = (Button)findViewById( R.id.loginMurid );

        btnLoginGuru.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent guru = new Intent( Home.this , LoginGuru.class );
                startActivity( guru );
            }
        } );

        btnLoginMurid.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent murid = new Intent( Home.this , LoginMurid.class );
                startActivity( murid );
            }
        } );
    }
}
