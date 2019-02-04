package com.example.asus.lesgo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.util.MarkEnforcingInputStream;
import com.example.asus.lesgo.Common.Common;
import com.example.asus.lesgo.Common.CommonDataDiri;
import com.example.asus.lesgo.model.ModelDataDiriMurid;
import com.example.asus.lesgo.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginMurid extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin;
    TextView btnRegisterMurid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login_murid);

        getSupportActionBar().setTitle( "Login Murid" );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        edtUsername = (EditText)findViewById( R.id.username );
        edtPassword = (EditText)findViewById( R.id.password );

        btnLogin = (Button)findViewById( R.id.loginMurid);

        btnRegisterMurid = (TextView) findViewById( R.id.registerMurid );

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference akun_user = database.getReference("User");

        btnRegisterMurid.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent murid = new Intent( LoginMurid.this , RegisterMurid.class );
                startActivity( murid );
            }
        } );

        btnLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mdialog = new ProgressDialog( LoginMurid.this );
                mdialog.setMessage( "Menunggu.." );
                mdialog.show();

                akun_user.addValueEventListener( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child( edtUsername.getText().toString() ).exists()) {
                            mdialog.dismiss();
                            User user = dataSnapshot.child( edtUsername.getText().toString() ).getValue( User.class );
                            ModelDataDiriMurid data = dataSnapshot.child( edtUsername.getText().toString() ).getValue(ModelDataDiriMurid.class );
                            if (user.getPassword().equals( edtPassword.getText().toString() )) {
                                Intent murid = new Intent( LoginMurid.this , MainActivityMurid.class );
                                Common.currentUser = user;
                                CommonDataDiri.currentUser = data;
                                startActivity(murid);
                                Toast.makeText( LoginMurid.this, "Login sukses", Toast.LENGTH_SHORT ).show();
                                finish();
                            } else {
                                Toast.makeText( LoginMurid.this, "Login gagal", Toast.LENGTH_SHORT ).show();
                            }
                        }
                        else {
                            mdialog.dismiss();
                            Toast.makeText(LoginMurid.this,"Username tidak ada", Toast.LENGTH_SHORT  ).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                } );
            }
        } );
    }


}

