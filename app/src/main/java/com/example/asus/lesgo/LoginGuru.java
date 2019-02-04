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

import com.example.asus.lesgo.Common.CommonDataDiriGuru;
import com.example.asus.lesgo.Common.CommonGuru;
import com.example.asus.lesgo.model.Guru;
import com.example.asus.lesgo.model.User;
import com.example.asus.lesgo.Common.Common;
import com.example.asus.lesgo.model.ModelDataDiriGuru;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginGuru extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin;
    TextView btnRegisterGuru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login_guru);

        getSupportActionBar().setTitle( "Login Guru" );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        edtUsername = (EditText)findViewById( R.id.username );
        edtPassword = (EditText)findViewById( R.id.password );

        btnLogin = (Button)findViewById( R.id.loginGuru);

        btnRegisterGuru = (TextView) findViewById( R.id.registerGuru );

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference akun_guru = database.getReference("Guru");

        btnRegisterGuru.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent guru = new Intent( LoginGuru.this , RegisterGuru.class );
                startActivity( guru );
            }
        } );

        btnLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mdialog = new ProgressDialog( LoginGuru.this );
                mdialog.setMessage( "Menunggu.." );
                mdialog.show();

                akun_guru.addValueEventListener( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child( edtUsername.getText().toString() ).exists()) {
                            mdialog.dismiss();
                            Guru guru = dataSnapshot.child( edtUsername.getText().toString() ).getValue( Guru.class );
                            ModelDataDiriGuru data = dataSnapshot.child( edtUsername.getText().toString() ).getValue(ModelDataDiriGuru.class );
                            if (guru.getPassword().equals( edtPassword.getText().toString() )) {
                                Intent intent = new Intent( LoginGuru.this , MainActivityGuru.class );
                                CommonGuru.currentGuru = guru;
                                CommonDataDiriGuru.currentUser = data;
                                startActivity( intent );
                                Toast.makeText( LoginGuru.this, "Login sukses", Toast.LENGTH_SHORT ).show();
                            } else {
                                Toast.makeText( LoginGuru.this, "Login gagal", Toast.LENGTH_SHORT ).show();
                            }
                        }
                        else {
                            mdialog.dismiss();
                            Toast.makeText(LoginGuru.this,"Username tidak ada", Toast.LENGTH_SHORT  ).show();
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
